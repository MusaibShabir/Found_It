package com.example.foundit.presentation.screens.input.lost

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foundit.presentation.data.firestore.FirestoreService
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class LostInputViewModel @Inject constructor(
    private val firestoreService: FirestoreService
) : ViewModel() {

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
    private val _parentSelectedCategoryId = MutableStateFlow("")
    val parentSelectedCategoryId: StateFlow<String> = _parentSelectedCategoryId.asStateFlow()

    fun setParentSelectedCategoryId(categoryId: String) {
        if (_parentSelectedCategoryId.value == categoryId) {
            _parentSelectedCategoryId.value = ""
        } else {
            _parentSelectedCategoryId.value = categoryId
        }
    }


    // Logic For Parent Category Selection
    private val _colorSelectedId = MutableStateFlow("")
    val colorSelectedId: StateFlow<String> = _colorSelectedId.asStateFlow()
    fun setColorSelectedIdId(colorId: String)  {
        if (_colorSelectedId.value == colorId) {
            _colorSelectedId.value = ""
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

    fun onSubmitClick() {
        viewModelScope.launch {
            val getChildCategoryIdsAsString = selectedChildCategoryIds.value.joinToString(", ")
            val getParentCategory = parentSelectedCategoryId.value
            val getColorCategory = colorSelectedId.value
            val getItemDescription = itemDescription.value

            firestoreService.addCardData(
                cardType = 0,
                childCategory = getChildCategoryIdsAsString,
                parentCategory = getParentCategory,
                color = getColorCategory,
                cardDescription = getItemDescription
            )
        }

    }

    // Map Screen Logic
    private val _markerPosition = MutableStateFlow<LatLng?>(null)
    var markerPosition: StateFlow<LatLng?> = _markerPosition.asStateFlow()

    fun updateMarkerPosition(latLng: LatLng) {
        _markerPosition.value = latLng
    }

    fun clearMarkerPosition() {
        _markerPosition.value = null
    }

    sealed class ResponseState<out T> {
        data object Idle : ResponseState<Nothing>()
        data object Loading : ResponseState<Nothing>()
        data class Success<T>(val data: T) : ResponseState<T>()
        data class Error(val exception: Exception) : ResponseState<Nothing>()
    }


    private val _markerAddressDetail = MutableStateFlow<ResponseState<Address>>(ResponseState.Idle)
    val markerAddressDetail = _markerAddressDetail.asStateFlow()

    // Create formattedAddress only once
    val formattedAddress: StateFlow<String> = markerAddressDetail.map { responseState ->
        when (responseState) {
            is ResponseState.Idle -> "Unknown"
            is ResponseState.Loading -> "Loading address..."
            is ResponseState.Success -> responseState.data.getAddressLine(0)
            is ResponseState.Error -> "Error fetching address: ${responseState.exception.message}"
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, "")

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun getMarkerAddressDetails(lat: Double, long: Double, context: Context) {
        viewModelScope.launch {
            _markerAddressDetail.value = ResponseState.Loading
            try {
                val geocoder = Geocoder(context, Locale.getDefault())
                geocoder.getFromLocation(lat, long, 1) { addresses ->
                    _markerAddressDetail.value = if (addresses.isNotEmpty()) {
                        ResponseState.Success(addresses[0])
                    } else {
                        ResponseState.Error(Exception("Address not found"))
                    }
                }
            } catch (e: Exception) {
                _markerAddressDetail.value = ResponseState.Error(e)
            }
        }
    }











}


