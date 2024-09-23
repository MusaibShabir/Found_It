package com.example.foundit.presentation.screens.profile

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foundit.presentation.data.account.AccountService
import com.example.foundit.presentation.data.local.repo.ProfileDataRepository
import com.example.foundit.presentation.data.local.tables.ProfileData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileDataRepository,
    private val accountService: AccountService
) : ViewModel() {

    private val _profileData = MutableStateFlow<ProfileData?>(null)
    val profileData: StateFlow<ProfileData?> = _profileData.asStateFlow()

    val userName: String
        get() = accountService.currentUserName


    val memberSince: Long?
        get() = accountService.a

    val profilePicture: Uri?
        get() = accountService.currentUserPhotoUrl

    init {
        viewModelScope.launch {
            profileRepository.getProfileData().collect { _profileData.value = it}
        }
    }

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

    /*
    fun updateProfileData(firstName: String, lastName: String, profilePicture: Uri?) {
        viewModelScope.launch {
            accountService.updateProfile(firstName, lastName, profilePicture)
        }
    }

    fun a(){
        Log.d("profile", "a: $userName")
        //Log.d("profile", "a: $profilePicture")
    }

    // Using StateFlow to hold and expose the username
    private val _userName = MutableStateFlow(userName)
    val userNames: StateFlow<String> = _userName.asStateFlow()

    fun aa(){
        _userName.value = userName
    }
    */

}
