package com.example.foundit.presentation.screens.registration.components.google

import android.content.Context
import android.util.Log
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foundit.R
import com.example.foundit.presentation.data.account.AccountService
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContinueWithGoogleViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {

    private fun getGoogleIdOption(context: Context): GetGoogleIdOption {
        //Getting String from Resources
        val webClientId = context.getString(R.string.web_client_id)
        return GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(webClientId)
            .build()
    }

    private fun getRequest(context: Context): GetCredentialRequest {
        val googleIdOption = getGoogleIdOption(context)
        return GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
    }

    fun getCredentials(
        credentialManager: CredentialManager,
        context: Context,
        onGetCredentialResponse: (Credential) -> Unit
    ) {
        viewModelScope.launch {
            try {
                // Retrieve the request dynamically
                val request = getRequest(context)
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