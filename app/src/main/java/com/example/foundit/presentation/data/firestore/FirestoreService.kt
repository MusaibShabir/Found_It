package com.example.foundit.presentation.data.firestore

interface FirestoreService {
    suspend fun addUserData(id:String, name:String, email: String, gender: String)
    suspend fun getUserData(userEmail: String, key: String = "", onResult: (String?) -> Unit = {})
}