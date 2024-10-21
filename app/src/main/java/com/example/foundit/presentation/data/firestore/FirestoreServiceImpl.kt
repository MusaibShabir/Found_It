package com.example.foundit.presentation.data.firestore

import android.util.Log
import com.example.foundit.presentation.data.account.AccountService
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
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
//    private var profileData: Map<String, Any>? = null

    // Method to add a listener to the "Profile" document
//    private suspend fun addProfileDataListener() {
//        val userId = currentUserId // Get the current user ID
//        if (userId.isNotEmpty()) {
//            firebaseFirestore.collection("User/$userId/Profile").document("profileData")
//                .get()
//                .addOnSuccessListener {
//                    profileData = it.data
//                }
//                .await()
//        } else {
//            Log.e("Firestore", "User ID is empty, cannot listen to profile data")
//        }
//    }

//    // Optional: Method to get the latest profile data
//    private fun getProfileData(): Map<String, Any>? {
//        return profileData
//    }

    // static data TODO to be reviewed
    override suspend fun addProfileData() {
        val userId = accountService.currentUserId
        if (userId.isEmpty()) {
            throw IllegalStateException("User ID is empty.")
        }

        // Reference to the document "User/{userId}/Profile/profileData"
        val documentRef = firebaseFirestore.collection("User/$userId/Profile")
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

//    val a: Long
//        get() = getProfileData()?.get("cardCount") as Long

    override suspend fun addCardData(
        cardType: Int,
        parentCategory: String,
        cardDescription: String,
        color: String,
        locationCoordinates: LatLng,
        locationAddress: String,
        childCategory: String,
        date: String,
        dateLong: Long
    ) {
        val userId = currentUserId

        // Ensure the user ID is not empty
        if (userId.isNotEmpty()) {
            // static data TODO to be reviewed or change update() to set() for profile count
            //addProfileData()
            try {
                // Start a Firestore transaction
                firebaseFirestore.runTransaction { transaction ->

                    // Reference to the Profile document
                    val profileDocRef = firebaseFirestore.collection("User/$userId/Profile")
                        .document("profileData")

                    // Fetch the profile document to get the current card count
                    val profileSnapshot = transaction.get(profileDocRef)
                    val currentCardCount = profileSnapshot.getLong("cardCount") ?: 0L

                    // Increment the card count
                    val newCardCount = currentCardCount + 1

                    // Update the Profile document with the new card count
                    transaction.update(profileDocRef, "cardCount", newCardCount)

                    // Prepare the data for the new card
                    val cardData = mapOf(
                        "cardId" to "$userId@$newCardCount",
                        "cardType" to cardType,
                        "parentCategory" to parentCategory,
                        "childCategory" to childCategory,
                        "color" to color,
                        "isMatchEmpty" to 1,
                        "cardDescription" to cardDescription,
                        "locationCoordinates" to GeoPoint(locationCoordinates.latitude, locationCoordinates.longitude),
                        "locationAddress" to locationAddress,
                        "cardCreatedDate" to Timestamp(Date()),
                        "date" to date,
                        "dateLong" to dateLong,
                        "matches" to null,
                        "status" to 0
                    )

                    // Reference to the new Card document
                    val cardDocRef = firebaseFirestore.collection("User/$userId/Card")
                        .document("$userId@$newCardCount")

                    // Set the new card data in the Card collection
                    transaction.set(cardDocRef, cardData, SetOptions.merge())

                }.await() // Await the transaction to ensure it completes successfully

//                Log.d("Firestore", "addItemData: Card added with id $userId@$newCardCount")

            } catch (e: Exception) {
                // Handle any exception during the transaction
                Log.e("Firestore", "Error adding item data: ${e.message}", e)
                throw e // Rethrow the exception to handle it appropriately
            }

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


    override suspend fun getCardData(): Flow<List<Map<String, Any>>> = callbackFlow {
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
                    .sortedByDescending { it["cardCreatedDate"].toString() } // Sort by the 'date' field (ensure it's properly formatted)
                Log.d("Firestore", "getItemData: $documents")
                trySend(documents) // Send the documents to the flow
            }
        }

        awaitClose { listener!!.remove() } // Clean up when the flow is closed
    }

    override suspend fun getMatchedCardData(cardId: List<String>): Flow<List<Map<String, Any>>> = callbackFlow {
        val userId = currentUserId
        if (userId.isEmpty()) {
            trySend(emptyList()) // Send an empty list if the user ID is not available
            close() // Close the flow as there's nothing to send
            return@callbackFlow
        }

        val documentRef = firebaseFirestore
            .collectionGroup("Card")
            .whereIn("cardId", cardId)  // Use whereIn to match a list of cardIds

        listener = documentRef.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                close(exception) // Close the flow with the exception
                return@addSnapshotListener
            }

            snapshot?.let { it ->
                val documents = it.documents
                    .map { document -> document.data ?: emptyMap() }
                    //.sortedByDescending { it["date"].toString() } // Sort by the 'date' field (ensure it's properly formatted)
                Log.d("Firestore", "getItemData: $documents")
                trySend(documents) // Send the documents to the flow
            }
        }

        awaitClose { listener!!.remove() } // Clean up when the flow is closed
    }

    override suspend fun getSingleCardData(cardId: String): Flow<Map<String, Any>> = callbackFlow {
        val userId = currentUserId
        if (userId.isEmpty()) {
            trySend(emptyMap()) // Send an empty map if the user ID is not available
            close() // Close the flow as there's nothing to send
            return@callbackFlow
        }

        val documentRef = firebaseFirestore
            .collection("User/${userId}/Card")
            .whereEqualTo("cardId", cardId) // Query for specific cardId

        val listenerRegistration = documentRef.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                close(exception) // Close the flow with the exception
                return@addSnapshotListener
            }

            snapshot?.let { querySnapshot ->
                // Assuming there's only one document with the given cardId
                val document = querySnapshot.documents.firstOrNull()
                if (document != null) {
                    val data = document.data ?: emptyMap<String, Any>()
                    trySend(data) // Send the document's data to the flow
                } else {
                    trySend(emptyMap()) // Send an empty map if no document is found
                }
            }
        }

        awaitClose { listenerRegistration.remove() } // Clean up when the flow is closed
    }

    override suspend fun getMatchedSingleCardData(cardId: String): Flow<Map<String, Any>> = callbackFlow{
        val userId = currentUserId
        if (userId.isEmpty()) {
            trySend(emptyMap()) // Send an empty map if the user ID is not available
            close() // Close the flow as there's nothing to send
            return@callbackFlow
        }

        val documentRef = firebaseFirestore
            .collectionGroup("Card")
            .whereEqualTo("cardId", cardId) // Query for specific cardId

        val listenerRegistration = documentRef.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                close(exception) // Close the flow with the exception
                return@addSnapshotListener
            }

            snapshot?.let { querySnapshot ->
                // Assuming there's only one document with the given cardId
                val document = querySnapshot.documents.firstOrNull()
                if (document != null) {
                    val data = document.data ?: emptyMap<String, Any>()
                    trySend(data) // Send the document's data to the flow
                } else {
                    trySend(emptyMap()) // Send an empty map if no document is found
                }
            }
        }
        awaitClose { listenerRegistration.remove() }
    }

    // if card match is confirmed as yes
//    override suspend fun cardMatched(foundCardId: String, lostCardId: String) {
//        val userId = currentUserId
//
//        // Ensure the user ID is not empty
//        if (userId.isNotEmpty()) {
//            try {
//                // Fetch the lost card document reference asynchronously before starting the transaction
//                val lostUserDocQuery = firebaseFirestore.collectionGroup("Card")
//                    .whereEqualTo("cardId", lostCardId)
//                    .get() // This returns a Task<QuerySnapshot>
//
//                // Await the query to get the lost document reference
//                val querySnapshot = lostUserDocQuery.await()
//
//                // Ensure that a matching document is found
//                if (querySnapshot.documents.isEmpty()) {
//                    throw IllegalStateException("No matching lost card document found.")
//                }
//
//                val lostUserDocRef = querySnapshot.documents[0].reference
//
//                // Now proceed with the transaction
//                firebaseFirestore.runTransaction { transaction ->
//
//                    // Reference to the Found document (for the current user)
//                    val foundUserDocRef = firebaseFirestore.collection("User/$userId/Card")
//                        .document(foundCardId)
//
//                    // Update code to set matches to null where matches == foundCardId
//                    // Write code here
//
//                    // Update the 'foundUserDocRef' document: set `status` to 1 and `matches` to lostCardId
//                    transaction.set(
//                        foundUserDocRef,
//                        mapOf(
//                            "status" to 1,
//                            "matches" to lostCardId
//                        ),
//                        SetOptions.merge()
//                    )
//
//                    // Update the 'lostUserDocRef' document: set `status` to 1 and `matches` to foundCardId
//                    transaction.set(
//                        lostUserDocRef,
//                        mapOf(
//                            "status" to 1,
//                            "matches" to foundCardId
//                        ),
//                        SetOptions.merge()
//                    )
//
//                    null // Return null to indicate success in the transaction
//                }.await() // Await the transaction to ensure it completes successfully
//
//                Log.d("Firestore", "Transaction successfully completed for foundCardId: $foundCardId and lostCardId: $lostCardId")
//
//            } catch (e: Exception) {
//                // Handle any exception during the transaction
//                Log.e("Firestore", "Error performing transaction: ${e.message}", e)
//                throw e
//            }
//        } else {
//            throw IllegalStateException("User ID is empty, cannot proceed with transaction")
//        }
//    }

    ///////           OR

    override suspend fun cardMatched(foundCardId: String, lostCardId: String) {
        val userId = currentUserId

        // Ensure the user ID is not empty
        if (userId.isNotEmpty()) {
            try {
                // Fetch the lost card document reference asynchronously before starting the transaction
                val lostUserDocQuery = firebaseFirestore.collectionGroup("Card")
                    .whereEqualTo("cardId", lostCardId)
                    .get() // This returns a Task<QuerySnapshot>

                // Await the query to get the lost document reference
                val querySnapshot = lostUserDocQuery.await()

                // Ensure that a matching document is found
                if (querySnapshot.documents.isEmpty()) {
                    throw IllegalStateException("No matching lost card document found.")
                }

                val lostUserDocRef = querySnapshot.documents[0].reference

                // Fetch all documents where matches == foundCardId before the transaction
                val matchesQuerySnapshot = firebaseFirestore.collectionGroup("Card")
                    .whereEqualTo("matches", foundCardId)
                    .get()
                    .await()

                // Now proceed with the transaction
                firebaseFirestore.runTransaction { transaction ->

                    val matchId = foundCardId.split('@')[0]

                    // Reference to the Found document (for the current user)
                    val foundUserDocRef = firebaseFirestore.collection("User/$matchId/Card")
                        .document(foundCardId)

                    // Update the 'matches' field to null for all documents where matches == foundCardId
                    for (doc in matchesQuerySnapshot.documents) {
                        val docRef = doc.reference
                        transaction.update(docRef, "matches", null)
                    }

                    // Update the 'foundUserDocRef' document: set `status` to 1 and `matches` to lostCardId
                    transaction.set(
                        foundUserDocRef,
                        mapOf(
                            "status" to 1,
                            "matches" to lostCardId
                        ),
                        SetOptions.merge()
                    )

                    // Update the 'lostUserDocRef' document: set `status` to 1 and `matches` to foundCardId
                    transaction.set(
                        lostUserDocRef,
                        mapOf(
                            "status" to 1,
                            "matches" to foundCardId
                        ),
                        SetOptions.merge()
                    )

                    null // Return null to indicate success in the transaction
                }.await() // Await the transaction to ensure it completes successfully

                Log.d("Firestore", "Transaction successfully completed for foundCardId: $foundCardId and lostCardId: $lostCardId")

            } catch (e: Exception) {
                // Handle any exception during the transaction
                Log.e("Firestore", "Error performing transaction: ${e.message}", e)
                throw e
            }
        } else {
            throw IllegalStateException("User ID is empty, cannot proceed with transaction")
        }
    }

    // OR

//    override suspend fun cardMatched(foundCardId: String, lostCardId: String) {
//        val userId = currentUserId
//
//        // Ensure the user ID is not empty
//        if (userId.isNotEmpty()) {
//            try {
//                // Fetch the lost card document reference asynchronously before starting the transaction
//                val lostUserDocQuery = firebaseFirestore.collectionGroup("Card")
//                    .whereEqualTo("cardId", lostCardId)
//                    .get() // This returns a Task<QuerySnapshot>
//
//                // Await the query to get the lost document reference
//                val querySnapshot = lostUserDocQuery.await()
//
//                // Ensure that a matching document is found
//                if (querySnapshot.documents.isEmpty()) {
//                    throw IllegalStateException("No matching lost card document found.")
//                }
//
//                val lostUserDocRef = querySnapshot.documents[0].reference
//
//                // Fetch all documents where matches == foundCardId before the transaction
//                val matchesQuerySnapshot = firebaseFirestore.collectionGroup("Card")
//                    .whereEqualTo("matches", foundCardId)
//                    .get()
//                    .await()
//
//                // Now proceed with the transaction
//                firebaseFirestore.runTransaction { transaction ->
//
//                    // Fetch the Found card document using collectionGroup for the current user
//                    val foundUserQuerySnapshot = firebaseFirestore.collectionGroup("Card")
//                        .whereEqualTo("cardId", foundCardId)
//                        .get()
//                        .await()
//
//                    if (foundUserQuerySnapshot.documents.isEmpty()) {
//                        throw IllegalStateException("No matching found card document found.")
//                    }
//
//                    val foundUserDocRef = foundUserQuerySnapshot.documents[0].reference
//
//                    // Update the 'matches' field to null for all documents where matches == foundCardId
//                    for (doc in matchesQuerySnapshot.documents) {
//                        val docRef = doc.reference
//                        transaction.update(docRef, "matches", null)
//                    }
//
//                    // Update the 'foundUserDocRef' document: set `status` to 1 and `matches` to lostCardId
//                    transaction.set(
//                        foundUserDocRef,
//                        mapOf(
//                            "status" to 1,
//                            "matches" to lostCardId
//                        ),
//                        SetOptions.merge()
//                    )
//
//                    // Update the 'lostUserDocRef' document: set `status` to 1 and `matches` to foundCardId
//                    transaction.set(
//                        lostUserDocRef,
//                        mapOf(
//                            "status" to 1,
//                            "matches" to foundCardId
//                        ),
//                        SetOptions.merge()
//                    )
//
//                    null // Return null to indicate success in the transaction
//                }.await() // Await the transaction to ensure it completes successfully
//
//                Log.d("Firestore", "Transaction successfully completed for foundCardId: $foundCardId and lostCardId: $lostCardId")
//
//            } catch (e: Exception) {
//                // Handle any exception during the transaction
//                Log.e("Firestore", "Error performing transaction: ${e.message}", e)
//                throw e
//            }
//        } else {
//            throw IllegalStateException("User ID is empty, cannot proceed with transaction")
//        }
//    }



    // if card match is confirmed as no
    override suspend fun cardNotMatched(foundCardId: String, lostCardId: String) {
        val userId = currentUserId

        // Ensure the user ID is not empty
        if (userId.isNotEmpty()) {
            try {
                // Fetch the lost card document reference asynchronously before starting the transaction
                val lostUserDocQuery = firebaseFirestore.collectionGroup("Card")
                    .whereEqualTo("cardId", lostCardId)
                    .get() // This returns a Task<QuerySnapshot>

                // Await the query to get the lost document reference
                val querySnapshot = lostUserDocQuery.await()

                // Ensure that a matching document is found
                if (querySnapshot.documents.isEmpty()) {
                    throw IllegalStateException("No matching lost card document found.")
                }

                val lostUserDocRef = querySnapshot.documents[0].reference

                // Now proceed with the transaction
                firebaseFirestore.runTransaction { transaction ->

                    val matchId = foundCardId.split('@')[0]

                    // Reference to the Found document (for the current user)
                    val foundUserDocRef = firebaseFirestore.collection("User/$matchId/Card")
                        .document(foundCardId)

                    // Get the current state of the found user's matches
                    val foundDocSnapshot = transaction.get(foundUserDocRef)
                    val currentMatches = foundDocSnapshot.get("matches") as? List<String> ?: emptyList()

                    // Determine the value for isMatchEmpty based on the current matches
                    val isMatchEmpty = if (currentMatches.size > 1) 0 else 1

                    // Update the 'foundUserDocRef' document: set `status` to 1 and `matches` to lostCardId
                    transaction.set(
                        foundUserDocRef,
                        mapOf(
                            "isMatchEmpty" to isMatchEmpty,
                            "matches" to FieldValue.arrayRemove(lostCardId)
                        ),
                        SetOptions.merge()
                    )

                    // Update the 'lostUserDocRef' document: set `status` to 1 and `matches` to foundCardId
                    transaction.set(
                        lostUserDocRef,
                        mapOf(
                            "isMatchEmpty" to 1,
                            "matches" to null
                        ),
                        SetOptions.merge()
                    )

                    null // Return null to indicate success in the transaction
                }.await() // Await the transaction to ensure it completes successfully

                Log.d("Firestore", "Transaction successfully completed for foundCardId: $foundCardId and lostCardId: $lostCardId")

            } catch (e: Exception) {
                // Handle any exception during the transaction
                Log.e("Firestore", "Error performing transaction: ${e.message}", e)
                throw e
            }
        } else {
            throw IllegalStateException("User ID is empty, cannot proceed with transaction")
        }
    }


    // ask confirmation from lost user
    override suspend fun contactLostUser(cardId: String) {
        val cardQuery = firebaseFirestore.collectionGroup("Card")
            .whereEqualTo("cardId", cardId)

        cardQuery.get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    for (document in querySnapshot.documents) {
                        // Update each document that matches the query
                        document.reference.update("isMatchEmpty", 0)
                            .addOnSuccessListener {
                                Log.d("Firestore", "DocumentSnapshot with ID ${document.id} successfully updated.")
                            }
                            .addOnFailureListener { e ->
                                Log.w("Firestore", "Error updating document with ID ${document.id}", e)
                            }
                    }
                } else {
                    Log.w("Firestore", "No document found with cardId: $cardId")
                }
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error fetching document with cardId: $cardId", e)
            }
    }


    override suspend fun deleteCardData(cardId: String) {
        val userId = currentUserId
        firebaseFirestore.collection("User/${userId}/Card").document(cardId).delete().await()
    }

    override suspend fun clearFirestoreListener() {
        listener?.remove()
        listener = null
    }
}
