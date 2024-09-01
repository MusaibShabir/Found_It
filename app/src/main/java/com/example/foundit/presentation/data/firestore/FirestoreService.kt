package com.example.foundit.presentation.data.firestore

interface FirestoreService {
    suspend fun addItemData(phone : String,model : String,color : String)
    suspend fun getItemData()
}