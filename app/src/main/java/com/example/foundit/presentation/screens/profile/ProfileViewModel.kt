package com.example.foundit.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foundit.presentation.data.firestore.FirestoreService
import com.example.foundit.presentation.data.local.repo.ProfileDataRepository
import com.example.foundit.presentation.data.local.tables.ProfileData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileDataRepository,
    private val firestoreService: FirestoreService
) : ViewModel() {

    private val _profileData = MutableStateFlow<ProfileData?>(null)
    val profileData: StateFlow<ProfileData?> = _profileData.asStateFlow()

    val name: String
        get() = firestoreService.currentUserId

//    val phone_FireStore = "Iphone"
//    val model_FireStore = "14"
//    val color_FireStore = "Red"

    init {
        viewModelScope.launch {
            profileRepository.getProfileData().collect { _profileData.value = it}
        }
    }

    //////// Firestore
    private val _firestoreItems = MutableStateFlow<List<Map<String, Any>>>(emptyList())
    val firestoreItems: StateFlow<List<Map<String, Any>>> = _firestoreItems.asStateFlow()

    init {
        viewModelScope.launch {
            profileRepository.getProfileData().collect { _profileData.value = it }
        }

        viewModelScope.launch {
            while (name.isEmpty()) {
                delay(100)
            }
            firestoreService.getItemData().collect { items ->
                val sortedItems = items.sortedBy { it["phone"]?.toString() }
                _firestoreItems.value = sortedItems
            }
        }
    }

    fun a(){
        viewModelScope.launch {
            while (name.isEmpty()) {
                delay(100)
            }
            firestoreService.getItemData().collect { items ->
                val sortedItems = items.sortedBy { it["phone"]?.toString() }
                _firestoreItems.value = sortedItems
            }
        }
    }
    ///////////

    fun upsertProfile(profileData: ProfileData) {
        viewModelScope.launch {
            profileRepository.upsertProfile(profileData)
            profileRepository.getProfileData().collect { _profileData.value = it}
        }
    }

    fun updateProfileData(id: Long, firstName: String, lastName: String) {
        viewModelScope.launch {
            profileRepository.updateProfileData(id, firstName, lastName)
        }
    }


}
