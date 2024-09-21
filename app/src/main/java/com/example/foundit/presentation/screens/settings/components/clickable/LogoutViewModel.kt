package com.example.foundit.presentation.screens.settings.components.clickable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foundit.presentation.data.account.AccountService
import com.example.foundit.presentation.data.firestore.FirestoreService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogoutViewModel @Inject constructor(
    private val accountService: AccountService,
    private val firestoreService: FirestoreService
) : ViewModel() {

    fun logout(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                accountService.logout()
                firestoreService.clearFirestoreListener()
                onResult(true)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }
}
