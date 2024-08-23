package com.example.foundit.presentation.data.firestore

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirestoreServiceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : FirestoreService {
    override suspend fun addUserData(id: String, name: String, email: String, gender: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getUserData(userEmail: String, key: String, onResult: (String?) -> Unit) {
        TODO("Not yet implemented")
    }
}