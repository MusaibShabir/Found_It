package com.example.foundit.presentation.data.firestore

import kotlinx.coroutines.flow.Flow

interface FirestoreService {
    val currentUserId : String
    suspend fun addItemData(cardType: Int, parentCategory: String, cardDescription: String, color: String, childCategory: String)
    suspend fun getItemData(): Flow<List<Map<String, Any>>>
    suspend fun clearFirestoreListener()
}
