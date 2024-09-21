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
class ProgressCardFullScreenViewModel @Inject constructor(
    private val firestoreService: FirestoreService
) : ViewModel() {

    // Holds the card data
    private val _cardData = MutableStateFlow<Map<String, Any>?>(emptyMap())
    val cardData: StateFlow<Map<String, Any>?> = _cardData

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
}