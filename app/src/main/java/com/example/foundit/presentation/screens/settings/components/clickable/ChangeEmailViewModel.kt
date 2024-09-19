package com.example.foundit.presentation.screens.settings.components.clickable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foundit.presentation.data.account.AccountService
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeEmailViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {
    val email
        get() = accountService.currentUserEmail

    val isGoogleProvider
        get() = accountService.currentUserProviderData?.any { it.providerId == GoogleAuthProvider.PROVIDER_ID } == true

    fun updateEmail(newEmail: String, onResult: (Boolean, Exception?) -> Unit) {
        viewModelScope.launch {
            try {
                accountService.updateEmail(newEmail)
                onResult(true, null)
            } catch (e: Exception) {
                onResult(false, e) // If there's an error, pass the exception
            }
        }
    }
}
