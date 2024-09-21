package com.example.foundit.presentation.data.firestore

import kotlinx.coroutines.flow.Flow

interface FirestoreService {
    val currentUserId : String
    suspend fun addCardData(cardType: Int, parentCategory: String, cardDescription: String, color: String, childCategory: String)
    suspend fun getCardData(): Flow<List<Map<String, Any>>>
    suspend fun getSingleCardData(cardId: String): Flow<Map<String, Any>>
    suspend fun deleteCardData(cardId: String)
    suspend fun clearFirestoreListener()
}
