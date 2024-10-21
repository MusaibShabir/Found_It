package com.example.foundit.presentation.data.firestore

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

interface FirestoreService {
    val currentUserId : String
    suspend fun addProfileData()
    suspend fun addCardData(cardType: Int, parentCategory: String, cardDescription: String, color: String, locationCoordinates: LatLng, locationAddress: String, childCategory: String, date: String, dateLong: Long)
    suspend fun getCardData(): Flow<List<Map<String, Any>>>
    suspend fun getMatchedCardData(cardId: List<String>): Flow<List<Map<String, Any>>>
    suspend fun getSingleCardData(cardId: String): Flow<Map<String, Any>>
    suspend fun getMatchedSingleCardData(cardId: String): Flow<Map<String, Any>>
    suspend fun cardMatched(foundCardId: String, lostCardId: String)
    suspend fun cardNotMatched(foundCardId: String, lostCardId: String)
    suspend fun contactLostUser(cardId: String)
    suspend fun deleteCardData(cardId: String)
    suspend fun clearFirestoreListener()
}
