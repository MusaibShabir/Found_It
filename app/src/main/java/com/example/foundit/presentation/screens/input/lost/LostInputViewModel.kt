package com.example.foundit.presentation.screens.input.lost

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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


    // Logic For Parent Category Selection
    private val _parentSelectedCategoryId = MutableStateFlow<Int?>(null)
    val parentSelectedCategoryId: StateFlow<Int?> = _parentSelectedCategoryId.asStateFlow()

    fun setParentSelectedCategoryId(categoryId: Int) {
        if (_parentSelectedCategoryId.value == categoryId) {
            _parentSelectedCategoryId.value = null
        } else {
            _parentSelectedCategoryId.value = categoryId
        }
    }


    // Logic For Parent Category Selection
    private val _colorSelectedId = MutableStateFlow<Int?>(null)
    val colorSelectedId: StateFlow<Int?> = _colorSelectedId.asStateFlow()
    fun setColorSelectedIdId(colorId: Int)  {
        if (_colorSelectedId.value == colorId) {
            _colorSelectedId.value = null
        } else {
            _colorSelectedId.value = colorId
        }
    }


    // Logic For Child Category Selection
    private val _selectedChildCategoryIds = MutableStateFlow<Set<Int>>(emptySet())
    val selectedChildCategoryIds: StateFlow<Set<Int>> = _selectedChildCategoryIds

    fun toggleChildCategorySelection(categoryId: Int) {
        _selectedChildCategoryIds.value = if (categoryId in _selectedChildCategoryIds.value) {
            _selectedChildCategoryIds.value - categoryId
        } else {
            _selectedChildCategoryIds.value + categoryId
        }
    }

    // Logic For Storing Item Description
    private val _itemDescription = MutableStateFlow("")
    val itemDescription: StateFlow<String> = _itemDescription.asStateFlow()

    fun updateItemDescription(newDescription: String) {
        _itemDescription.value = newDescription
    }

    //Logic To get All the Data from the User by ID
    fun getAllUserDataByCategoryIds(): Map<String, Any?> {
        return mapOf(
            "parentSelectedCategoryId" to parentSelectedCategoryId.value,
            "colorSelectedId" to colorSelectedId.value,
            "selectedChildCategoryIds" to selectedChildCategoryIds.value
        )

    }
}


