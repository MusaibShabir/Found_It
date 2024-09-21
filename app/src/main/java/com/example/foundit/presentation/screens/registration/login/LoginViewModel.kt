package com.example.foundit.presentation.screens.registration.login

import android.util.Log
import androidx.credentials.Credential
import androidx.credentials.CustomCredential
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foundit.presentation.data.account.AccountService
import com.example.foundit.presentation.data.firestore.FirestoreService
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService,
    private val firestoreService: FirestoreService
) : ViewModel()
{
    fun login(
        email: String,
        password: String,
        onResult: (Boolean,Exception?) -> Unit)
    {
        viewModelScope.launch {
            try {
                accountService.login(email,password)
                onResult(true,null)
            } catch (e: Exception) {
                onResult(false,e)
            }
        }
    }
    /*
    fun onSignInWithGoogle(credential: Credential, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                    accountService.signInWithGoogle(googleIdTokenCredential.idToken)
                    onResult(true)
                } else {
                    Log.d("Login", "Invalid credential type")
                    onResult(false)
                }
            } catch (e: Exception) {
                Log.d("Login", "google (viewModel) $e")
                onResult(false)
            }
        }
    }

     */

    fun onSignInWithGoogle(
        credential: Credential,
        onResult: (Boolean) -> Unit)
    {
        viewModelScope.launch {
            try {
                if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                    Log.d("LoginViewModel", "Received Google ID token: ${googleIdTokenCredential.idToken}")

                    accountService.signInWithGoogle(googleIdTokenCredential.idToken)
                    Log.d("LoginViewModel", "Google sign-in successful")
                    firestoreService.addProfileData()
                    onResult(true)
                } else {
                    Log.d("LoginViewModel", "Invalid credential type: ${credential.type}")
                    onResult(false)
                }
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Error during Google sign-in", e) // Use Log.e for errors
                onResult(false)
            }
        }
    }
}


