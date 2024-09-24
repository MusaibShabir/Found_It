package com.example.foundit.presentation.screens.registration.signup

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.credentials.Credential
import androidx.lifecycle.viewModelScope
import com.example.foundit.presentation.data.account.AccountService
import com.example.foundit.presentation.data.firestore.FirestoreService
import com.example.foundit.presentation.screens.registration.RegistrationBaseViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
    private val firestoreService: FirestoreService
) : RegistrationBaseViewModel() {

    //First Name
    private val _firstName = MutableStateFlow("")
    val firstName = _firstName.asStateFlow()

    fun onFirstNameChange(value: String) {
        _firstName.value = value
    }


    //Last Name
    private val _lastName = MutableStateFlow("")
    val lastName = _lastName.asStateFlow()

    fun onLastNameChange(value: String) {
        _lastName.value = value
    }


    //Gender
    private val _gender = MutableStateFlow("")
    val gender = _gender.asStateFlow()

    fun onGenderChange(value: String) {
        _gender.value = value
    }


    //SignUp Button
    fun signUpUser(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        onResult: (Boolean, Exception?) -> Unit
    ) {
        viewModelScope.launch {
                try {
                    accountService.createAccount(email,password)
                    accountService.sendEmailVerification()
                    accountService.update(firstName,lastName)
                    firestoreService.addProfileData()
                    onResult(true,null)
                } catch (e: Exception) {
                    onResult(false,e)
                }
            }
        }

    sealed class SignInResult {
        data object Success : SignInResult()
        data class Failure(val errorCode: Int, val errorMessage: String) : SignInResult()
    }

    fun onSignUpWithGoogle(
        credential: Credential,
        onResult: (SignInResult) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                accountService.signInWithGoogle(googleIdTokenCredential.idToken)
                firestoreService.addProfileData()
                onResult(SignInResult.Success)
            } catch (e: Exception) {
                val (errorCode, errorMessage) = when (e) {
                    is FirebaseAuthInvalidCredentialsException -> {
                        Pair(101, "Invalid credentials.")
                    }
                    is FirebaseAuthUserCollisionException -> {
                        Pair(102, "An account already exists with this email.")
                    }
                    is FirebaseNetworkException -> {
                        Pair(201, "Network error. Please check your connection.")
                    }// ... handle other specific exceptions
                    else -> {
                        Pair(999, "An unknown error occurred.")
                    }
                }
                onResult(SignInResult.Failure(errorCode, errorMessage))
            }
        }
    }
}