package com.example.foundit.presentation.screens.settings.components.clickable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foundit.presentation.data.account.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteAccountViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {

    fun deleteAccount(onResult: (Boolean, Exception?) -> Unit) {
        viewModelScope.launch {
            try {
                accountService.deleteAccount() // Attempt to delete the user account
                onResult(true, null) // If successful, pass success
            } catch (e: Exception) {
                onResult(false, e) // If there's an error, pass the exception
            }
        }
    }
}
