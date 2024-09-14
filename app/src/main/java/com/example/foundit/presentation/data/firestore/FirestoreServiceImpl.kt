package com.example.foundit.presentation.data.firestore

import android.util.Log
import com.example.foundit.presentation.data.account.AccountService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirestoreServiceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val accountService: AccountService
) : FirestoreService {

    override val currentUserId: String
        get() = accountService.currentUserId

    private var listener: ListenerRegistration? = null

    override suspend fun addItemData(phone: String, model: String, color: String) {
        val data = mapOf(
            "phone" to phone,
            "model" to model,
            "color" to color,
            "status" to "pending"
        )

        val userId = currentUserId
        if (userId.isNotEmpty()) {
            firebaseFirestore.collection("User/$userId/Card")
                .document(phone)
                .set(data, SetOptions.merge())
        } else {
            throw IllegalStateException("User ID is empty, cannot add data")
        }
    }

    override suspend fun getItemData(): Flow<List<Map<String, Any>>> = callbackFlow {
        val userId = currentUserId
        if (userId.isEmpty()) {
            trySend(emptyList()) // Send an empty list if the user ID is not available
            close() // Close the flow as there's nothing to send
            return@callbackFlow
        }

        val documentRef = firebaseFirestore.collection("User/${userId}/Card")

        listener?.remove()
        listener = documentRef.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                close(exception) // Close the flow with the exception
                return@addSnapshotListener
            }

            snapshot?.let {
                val documents = it.documents.map { document -> document.data ?: emptyMap() }
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