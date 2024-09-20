package com.example.foundit.presentation.screens.input.lost

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LostInputViewModel @Inject constructor() : ViewModel() {

    private val collectedData = mutableStateOf<InputLostData?>(null)


    // Function to update the collected data
    fun updateCollectedData(newData: InputLostData) {
        collectedData.value = newData
    }

    // Function to send data to FireStore (you'll implement this)
    fun sendDataToFirestore() {
        val dataToSend = collectedData.value ?: return // Ensure data is available

        // ... your FireStore logic to send dataToSend
    }

    // Remembered array to store integers
    private val integerArray =  mutableStateListOf<Int>()

    fun addIntegerToArray(newValue: Int) {
        integerArray.add(newValue)
        Log.d( "ViewModel", "Data Array: $integerArray")
        println(integerArray)
    }

    // Logic of Single Category Selection
    var selectedCategoryId by mutableStateOf<Int?>(null) // Store the selected category ID

    fun setSelectedCategoryId(categoryId: Int) {
        selectedCategoryId = categoryId
    }

    private val _selectedChildCategoryIds = MutableStateFlow<Set<Int>>(emptySet())
    val selectedChildCategoryIds: StateFlow<Set<Int>> = _selectedChildCategoryIds

    // Function to toggle child category selection
    fun toggleChildCategorySelection(categoryId: Int) {
        _selectedChildCategoryIds.value = if (categoryId in _selectedChildCategoryIds.value) {
            _selectedChildCategoryIds.value - categoryId
        } else {
            _selectedChildCategoryIds.value + categoryId
        }
    }
}


