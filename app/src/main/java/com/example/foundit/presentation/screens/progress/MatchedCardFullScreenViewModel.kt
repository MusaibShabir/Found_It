package com.example.foundit.presentation.screens.progress

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foundit.presentation.data.firestore.FirestoreService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchedCardFullScreenViewModel @Inject constructor(
    private val firestoreService: FirestoreService,
) : ViewModel() {

    // Holds the card data
    private val _cardData = MutableStateFlow<Map<String, Any>?>(emptyMap())
    val cardData: StateFlow<Map<String, Any>?> = _cardData

    // Fetch data for the given cardId
    fun fetchCardData(cardId: String) {
        viewModelScope.launch {
            firestoreService.getMatchedSingleCardData(cardId)
                .catch {
                    _cardData.value = emptyMap()
                    Log.d("dataCard", "Error fetching data: ${it.message}")
                }
                .collect { data ->
                    _cardData.value = data
                    Log.d("dataCard", "fetchCardData: ${_cardData.value}")
                }
            Log.d("dataCard", "fetchCardData after collect: ${_cardData.value}")
        }
    }

    fun contactUser(cardId: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            //Log.d("Firestore", "DocumentSnapshot with ID $cardId successfully updated.")
            firestoreService.contactLostUser(cardId)
            onResult(true)
        }
    }




}