package com.example.foundit.presentation.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foundit.presentation.data.account.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {

    fun onAppStart(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val user = accountService.hasUser()
                onResult(user)
            } catch (e: Exception) {
                onResult(false)
                Log.d("Session", "Session error: ${e.message}")
            }
        }
    }
}