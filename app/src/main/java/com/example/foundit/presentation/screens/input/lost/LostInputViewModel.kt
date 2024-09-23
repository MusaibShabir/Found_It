package com.example.foundit.presentation.screens.input.lost

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foundit.presentation.data.firestore.FirestoreService
import com.example.foundit.presentation.screens.input.data.GeocodingResponse
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException
import javax.inject.Inject

@HiltViewModel
class LostInputViewModel @Inject constructor(
    private val firestoreService: FirestoreService
) : ViewModel() {

    // Handling the Card Type Logic
    private val _cardType = MutableStateFlow<Int?>(null)
    val cardType: StateFlow<Int?> = _cardType

        // Function to store cardType
    fun storeCardType(type: Int) {
        _cardType.value = type
    }


    // Remembered array to store integers
    private val integerArray =  mutableStateListOf<Int>()

    fun addIntegerToArray(newValue: Int) {
        integerArray.add(newValue)
        Log.d( "ViewModel", "Data Array: $integerArray")
        println(integerArray)
    }


    // Logic For Parent Category Selection Screen
    private val _parentSelectedCategoryId = MutableStateFlow("")
    val parentSelectedCategoryId: StateFlow<String> = _parentSelectedCategoryId.asStateFlow()

    fun setParentSelectedCategoryId(categoryId: String) {
        if (_parentSelectedCategoryId.value == categoryId) {
            _parentSelectedCategoryId.value = ""
        } else {
            _parentSelectedCategoryId.value = categoryId
        }
    }

    // Logic For Color Category Selection Screen
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
        //_markerAddressDetail.value = ResponseState.Idle

    }

    private val apiKey = "AIzaSyB2PRqP3v51ke2IKYdi8pM2Katkfeb0p3A"

    private val _address = MutableStateFlow<String?>(null)
    val address: StateFlow<String?> = _address.asStateFlow()


    fun fetchAddressFromGeocodingApi(
        latitude: Double?,
        longitude: Double?
    ) {
        viewModelScope.launch {
            if (latitude != null && longitude != null) {
                Log.d("GeoCoding", "Latitude: $latitude, Longitude: $longitude")

                // Prepare the URL for the API request
                val url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=$latitude,$longitude&key=$apiKey"

                // Create an OkHttpClient instance
                val client = OkHttpClient()

                // Build the request
                val request = Request.Builder()
                    .url(url)
                    .build()

                // Make the network call asynchronously
                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: okhttp3.Call, e: IOException) {
                        Log.d("GeoCoding", "Failed to make the request: ${e.message}")
                        _address.value = null
                    }

                    override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                        val responseBody = response.body?.string()
                        if (!response.isSuccessful || responseBody == null) {
                            Log.d("GeoCoding", "Failed to fetch the address")
                            _address.value = null
                            return
                        }

                        // Parse the JSON response using Kotlin serialization
                        val json = Json { ignoreUnknownKeys = true }
                        val geocodingResponse = json.decodeFromString<GeocodingResponse>(responseBody)

                        // Extract the desired address components
                        val addressComponents = geocodingResponse.results.firstOrNull()?.address_components
                        val subLocality = addressComponents?.firstOrNull { it.types.contains("sublocality_level_1") }?.long_name
                        val locality = addressComponents?.firstOrNull { it.types.contains("locality") }?.long_name
                        val administrativeAreaLevel1 = addressComponents?.firstOrNull { it.types.contains("administrative_area_level_1") }?.long_name
                        val country = addressComponents?.firstOrNull { it.types.contains("country") }?.long_name
                        val postalCode = addressComponents?.firstOrNull { it.types.contains("postal_code") }?.long_name

                        // Combine the components into a single string, handling empty cases
                        val filteredAddress = if (
                            subLocality.isNullOrEmpty() &&
                            locality.isNullOrEmpty() &&
                            administrativeAreaLevel1.isNullOrEmpty() &&
                            country.isNullOrEmpty()) {
                            postalCode ?: "Unknown Location"
                        } else {
                            listOfNotNull(subLocality, locality, administrativeAreaLevel1, country).joinToString(", ")
                        }

                        // Update the _address StateFlow with the filtered address
                        _address.value = filteredAddress
                    }
                })
            } else {
                Log.d("GeoCoding", "Latitude or Longitude is null")
                _address.value = null
            }
        }
    }


}



