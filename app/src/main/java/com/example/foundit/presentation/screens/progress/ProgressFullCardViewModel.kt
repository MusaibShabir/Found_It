package com.example.foundit.presentation.screens.progress

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
class ProgressFullCardViewModel @Inject constructor(
    private val firestoreService: FirestoreService
) : ViewModel() {

    // Holds the card data
    private val _cardData = MutableStateFlow<Map<String, Any>?>(null)
    val cardData: StateFlow<Map<String, Any>?> = _cardData

    // Fetch data for the given cardId
    fun fetchCardData(cardId: String) {
        viewModelScope.launch {
            firestoreService.getItemData(cardId)
                .catch {
                    // Handle any errors
                    _cardData.value = emptyMap()
                }
                .collect { data ->
                    _cardData.value = data
                }
        }
    }
}