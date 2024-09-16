package com.example.foundit.presentation.data.firestore

import android.util.Log
import com.example.foundit.presentation.data.account.AccountService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.Date
import javax.inject.Inject

class FirestoreServiceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val accountService: AccountService
) : FirestoreService {

    override val currentUserId: String
        get() = accountService.currentUserId

    private var listener: ListenerRegistration? = null

    // Variable to hold the profile data
    private var profileData: Map<String, Any>? = null

    // Method to add a listener to the "Profile" document
    private suspend fun addProfileDataListener() {
        val userId = currentUserId // Get the current user ID
        if (userId.isNotEmpty()) {
            firebaseFirestore.collection("User/$userId/Profile").document("profileData")
                .get()
                .addOnSuccessListener {
                    profileData = it.data
                }
                .await()
        } else {
            Log.e("Firestore", "User ID is empty, cannot listen to profile data")
        }
    }

    // Optional: Method to get the latest profile data
    private fun getProfileData(): Map<String, Any>? {
        return profileData
    }

    // static data TODO to be reviewed
    private suspend fun ensureProfileDataExists() {
        val userId = accountService.currentUserId
        if (userId.isEmpty()) {
            throw IllegalStateException("User ID is empty.")
        }

        // Reference to the document "User/{userId}/Profile/profileData"
        val documentRef = firebaseFirestore.collection("User")
            .document(userId)
            .collection("Profile")
            .document("profileData")

        try {
            // Check if the document exists
            val documentSnapshot = documentRef.get().await()

            if (!documentSnapshot.exists()) {
                // Document does not exist, set the initial data
                val data = mapOf(
                    "cardCount" to 0
                )

                // Set the data with SetOptions.merge() to ensure it merges with existing data if any
                documentRef.set(data, SetOptions.merge()).await()
                println("ProfileData document created with initial data")
            } else {
                println("ProfileData document already exists")
            }
        } catch (e: Exception) {
            // Handle potential errors (network issues, permission issues, etc.)
            println("Error checking or setting profile data: ${e.message}")
        }
    }

    val a: Long
        get() = getProfileData()?.get("cardCount") as Long
    override suspend fun addItemData(phone: String, model: String, color: String) {

        // static data TODO to be reviewed
        ensureProfileDataExists()

        addProfileDataListener()
        val data = mapOf(
            "cardId" to "$currentUserId@${getProfileData()?.get("cardCount")}",
            "cardType" to 0,
            "phone" to phone,
            "model" to model,
            "color" to color,
            "status" to 0,
            "date" to Date()
        )

        val userId = currentUserId
        if (userId.isNotEmpty()) {
            firebaseFirestore.collection("User/$userId/Card")
                .document("$userId@${(a+1)}")
                .set(data, SetOptions.merge())
            firebaseFirestore.collection("User/$userId/Profile").document("profileData").update("cardCount",
                (a+1)).await()
            Log.d("uuid", "addItemData: $currentUserId@${getProfileData()?.get("cardCount")}")
        } else {
            throw IllegalStateException("User ID is empty, cannot add data")
        }
    }

    /*
    override suspend fun getItemData(): Flow<List<Map<String, Any>>> = callbackFlow {
        if (accountService.currentUserId.isEmpty()) {
            trySend(emptyList()) // Send an empty list if the user ID is not available
            close() // Close the flow as there's nothing to send
            return@callbackFlow
        }

        val documentRef = firebaseFirestore.collection("User/${accountService.currentUserId}/Card")

        val listener = documentRef.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                close(exception)
                return@addSnapshotListener
            }
            if (snapshot != null) {
                // Process the documents, filter by status, and sort by the date field
                val documents = snapshot.documents
                    .map { it.data ?: emptyMap() }
                    //.sortedBy { it["date"].toString() } // Sort by the 'date' field (ensure it's properly formatted)
                trySend(documents) // Send the processed list through the callback flow
            }
        }

        awaitClose { listener.remove() }
    }

    */


    override suspend fun getItemData(): Flow<List<Map<String, Any>>> = callbackFlow {
        val userId = currentUserId
        if (userId.isEmpty()) {
            trySend(emptyList()) // Send an empty list if the user ID is not available
            close() // Close the flow as there's nothing to send
            return@callbackFlow
        }

        val documentRef = firebaseFirestore.collection("User/${userId}/Card")

        listener = documentRef.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                close(exception) // Close the flow with the exception
                return@addSnapshotListener
            }

            snapshot?.let { it ->
                val documents = it.documents
                    .map { document -> document.data ?: emptyMap() }
                    .sortedByDescending { it["date"].toString() } // Sort by the 'date' field (ensure it's properly formatted)
                Log.d("Firestore", "getItemData: $documents")
                trySend(documents) // Send the documents to the flow
            }
        }

        awaitClose { listener!!.remove() } // Clean up when the flow is closed
    }

    override suspend fun clearFirestoreListener() {
        listener?.remove()
        listener = null
    }
}
