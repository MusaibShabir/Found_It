package com.example.foundit.presentation.data.firestore

import kotlinx.coroutines.flow.Flow

interface FirestoreService {
    suspend fun addItemData(phone : String,model : String,color : String)
    suspend fun getItemData(): Flow<List<Map<String, Any>>>
}