package com.example.foundit.presentation.screens.registration.signup

import android.util.Log
import androidx.credentials.Credential
import androidx.lifecycle.viewModelScope
import com.example.foundit.di.FireBaseModule
import com.example.foundit.presentation.data.account.AccountService
import com.example.foundit.presentation.screens.registration.RegistrationBaseViewModel
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService
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
    fun signUpUser() {
        viewModelScope.launch {

        }
    }

    fun createAccount(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {

            try {
                accountService.createAccount(email,password)
                accountService.sendEmailVerification()
                update(firstName,lastName)
                onResult(true)
            } catch (e: Exception) {
                onResult(false)
                Log.d("SignUp", "login error: ${e.message}")
            }

//            auth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener { task ->
//                    if (task.isSuccessful) {
//                        sendEmailVerification()
//                        update(
//                            firstName,lastName
//                        )
//                        onResult(true)
//                    } else {
//                        onResult(false)
//                    }
//                }
        }
    }

    private val firebaseAuth: FirebaseAuth = FireBaseModule.provideFirebaseAuth()

    private fun update(
        firstName: String,
        lastName: String,
    ) {
        val profileUpdates = userProfileChangeRequest {
            displayName = "$firstName  $lastName"
        }

        firebaseAuth.currentUser!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("SignUp", "User profile updated.")
                }
            }
    }

//    private fun sendEmailVerification() {
//        val user = auth.currentUser
//        user?.sendEmailVerification()
//            ?.addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    Log.d("SignUp", "email sent")
//                    // Email Verification sent
//                } else {
//                    Log.d("SignUp", "email error")
//                    // Handle failure
//                }
//            }
//    }

    fun onSignUpWithGoogle(
        credential: Credential,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            try {
                //if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                accountService.signInWithGoogle(googleIdTokenCredential.idToken)
                onResult(true)

            } catch (e: Exception) {
                onResult(false)
            }
        }
    }


}