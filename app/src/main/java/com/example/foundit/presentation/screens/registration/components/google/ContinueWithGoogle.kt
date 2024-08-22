package com.example.foundit.presentation.screens.registration.components.google

import android.content.Context
import android.util.Log
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foundit.presentation.data.account.AccountService
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContinueWithGoogle   @Inject constructor(
    private val accountService: AccountService
) :ViewModel() {


    private val googleIdOption = GetGoogleIdOption.Builder()
        .setFilterByAuthorizedAccounts(false)
        .setServerClientId("513829197730-vk4nd6ioipo4fvmaci6e0e5utflejga2.apps.googleusercontent.com")
        .build()

    private val request = GetCredentialRequest.Builder()
        .addCredentialOption(googleIdOption)
        .build()


    fun getCredentials(
        credentialManager: CredentialManager,
        context: Context,
        onGetCredentialResponse: (Credential) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val result = credentialManager.getCredential(
                    request = request,
                    context = context
                )
                onGetCredentialResponse(result.credential)
            } catch (e: GetCredentialException) {
                Log.d("error", e.message.orEmpty())
            }
        }
    }

}