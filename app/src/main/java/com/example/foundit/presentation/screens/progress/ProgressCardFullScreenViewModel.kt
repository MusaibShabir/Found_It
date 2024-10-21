package com.example.foundit.presentation.screens.progress

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foundit.di.NotificationHelper
import com.example.foundit.presentation.data.firestore.FirestoreService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProgressCardFullScreenViewModel @Inject constructor(
    private val firestoreService: FirestoreService,
    private val notificationHelper: NotificationHelper
) : ViewModel() {

    // Holds the card data
    private val _cardData = MutableStateFlow<Map<String, Any>?>(emptyMap())
    val cardData: StateFlow<Map<String, Any>?> = _cardData

    // hold matched cards
    private val _matchedCards = MutableStateFlow<List<Map<String, Any>>>(emptyList())
    val matchedCards: StateFlow<List<Map<String, Any>>> = _matchedCards.asStateFlow()

    // Fetch data for the given cardId
    fun fetchCardData(cardId: String) {
        viewModelScope.launch {
            firestoreService.getSingleCardData(cardId)
                .catch {
                    // Handle any errors
                    _cardData.value = emptyMap()
                    Log.d("dataCard", "Error fetching data: ${it.message}")
                }
                .collect { data ->
                    _cardData.value = data
                    // Log inside the collect block to ensure data has been fetched
                    Log.d("dataCard", "fetchCardData: ${_cardData.value}")
                }

            // This log will still show the old data (emptyMap) as it executes before collect completes
            Log.d("dataCard", "fetchCardData after collect: ${_cardData.value}")
        }
    }

    // Fetch data for the given cardId
    fun fetchMatchedCards(cardId: List<String>) {
        viewModelScope.launch {
//            Log.d("profile", "ProgressCardFullScreen: $cardId")
            while (cardId.isEmpty()){
                delay(100)
            }
//            Log.d("profile", "ProgressCardFullScreen after: $cardId")
            firestoreService.getMatchedCardData(cardId).collect { items ->
                _matchedCards.value = items
//                Log.d("profile", "ProgressCardFullScreen after: $items")
//                Log.d("profile", "ProgressCardFullScreen after: ${_matchedCards.value}")
            }
        }
    }

    // Fetch data for the given cardId
    fun cardMatched(foundCardId: String, lostCardId: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            firestoreService.cardMatched(foundCardId, lostCardId)
            onResult(true)
        }
    }

    // Fetch data for the given cardId
    fun cardNotMatched(foundCardId: String, lostCardId: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            firestoreService.cardNotMatched(foundCardId, lostCardId)
            onResult(true)
        }
    }

    fun deleteCardData(cardId: String, onResult: (Boolean, Exception?) -> Unit){
        viewModelScope.launch {
            try {
                firestoreService.deleteCardData(cardId)
                onResult(true,null)
            } catch (e: Exception) {
                onResult(false,e)
            }
        }
    }

    fun triggerNotification() {
        notificationHelper.showNotification(
            title = "New Match",
            content = "Match Found for your Item")
    }


}