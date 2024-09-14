package com.example.foundit.presentation.data.firestore

import com.example.foundit.presentation.data.account.AccountService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirestoreServiceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val accountService: AccountService
) : FirestoreService {

    override suspend fun addItemData(phone: String, model: String, color: String) {
        val data = hashMapOf(
            "phone" to phone,
            "model" to model,
            "color" to color,
            "status" to "pending",
        )

        firebaseFirestore.collection("User/${accountService.currentUserId}/Card")
            .document(phone)
            .set(data, SetOptions.merge())
    }

    override suspend fun getItemData(): Flow<List<Map<String, Any>>> = callbackFlow {
        val documentRef = firebaseFirestore.collection("User/${accountService.currentUserId}/Card")
        //val documentRef = firebaseFirestore.collectionGroup("Card")

        val listener = documentRef.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                close(exception)
                return@addSnapshotListener
            }
            if (snapshot != null){
                val documents = snapshot.documents.map { it.data ?: emptyMap() }
                trySend(documents)
            }
        }

        awaitClose { listener.remove() }
    }
}