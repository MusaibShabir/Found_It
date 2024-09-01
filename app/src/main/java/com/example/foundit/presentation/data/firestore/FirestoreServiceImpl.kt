package com.example.foundit.presentation.data.firestore

import android.util.Log
import com.example.foundit.presentation.data.account.AccountService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
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
            "status" to "pending"
        )

        firebaseFirestore.collection("User/${accountService.currentUserId}/Card")
            .document("itemData")
            .set(data, SetOptions.merge())
    }

    override suspend fun getItemData() {
        val documentRef = firebaseFirestore.collection("User/${accountService.currentUserId}/Card")
            .document("itemData")

        documentRef.addSnapshotListener { document, exception ->
            if (exception != null) {
                Log.d("Firestore", "Listen failed.", exception)
                return@addSnapshotListener
            }

            //// review pending
            if (document != null && document.exists()) {
                try {
                    Log.d("Firestore", "DocumentSnapshot1 data: ${document.data}")
                } catch (e: Exception) {
                    Log.d("Firestore", "DocumentSnapshot1 data: $e")
                }
            } else {
                Log.d("Firestore", "No such document")
            }
        }
    }
}