package com.example.foundit.presentation.screens.settings.components.clickable

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foundit.presentation.data.account.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogoutViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {

    fun logout(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                accountService.logout()
                onResult(true)
            } catch (e: Exception) {
                onResult(false)
                Log.d("Logout", "logout error: ${e.message}")
            }
        }
    }
}
