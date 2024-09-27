package com.example.foundit.presentation.screens.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foundit.presentation.data.account.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {


    val previoususerId = accountService.currentUserId

    val currentuserId: String
        get() = accountService.currentUserId

    private val currentUserName: String
        get() = accountService.currentUserName

    val memberSince: Long?
        get() = accountService.accountCreationDate

    private val currentUserProfilePicture: Uri?
        get() = accountService.currentUserPhotoUrl

    // dividing user name into firstName and lastName
    private val splitUserName: List<String>
        get() = currentUserName.split(" ", limit = 2)

    private val userNameList: List<String>
        get() {
            return if (splitUserName.size == 2) listOf(splitUserName[0], splitUserName[1]) else listOf(splitUserName[0], "")
        }


    // Using StateFlow to hold and expose the username
    private val _userFirstName = MutableStateFlow(userNameList[0])
    val userFirstNames: StateFlow<String> = _userFirstName.asStateFlow()

    private val _userLastName = MutableStateFlow(userNameList[1])
    val userLastNames: StateFlow<String> = _userLastName.asStateFlow()

    // Using StateFlow to hold and expose the profile picture
    private val _profilePicture = MutableStateFlow(currentUserProfilePicture)
    val profilePicture: StateFlow<Uri?> = _profilePicture.asStateFlow()


    fun updateProfileData(firstName: String, lastName: String, profilePicture: Uri?) {
        viewModelScope.launch {
            accountService.updateProfile(firstName, lastName, profilePicture)
            updateProfileData()
        }
    }

    fun updateProfileData(){
//        splitUserName = currentUserName.split(" ", limit = 2)
        _userFirstName.value = userNameList[0]
        _userLastName.value = userNameList[1]
        _profilePicture.value = currentUserProfilePicture
//        Log.d("profile", "aa _profilepicture: ${_profilePicture.value}")
//        Log.d("profile", "a split: ${splitUserName}")
//        Log.d("profile", "a list: ${userNameList}")
//        Log.d("profile", "a _flow: ${_userFirstName.value}")
//        Log.d("profile", "a flow: ${userLastNames.value}")
    }

}
