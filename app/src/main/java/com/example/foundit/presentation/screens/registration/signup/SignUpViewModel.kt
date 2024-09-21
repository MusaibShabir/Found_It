package com.example.foundit.presentation.screens.registration.signup

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.credentials.Credential
import androidx.lifecycle.viewModelScope
import com.example.foundit.di.FireBaseModule
import com.example.foundit.presentation.data.account.AccountService
import com.example.foundit.presentation.screens.registration.RegistrationBaseViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.userProfileChangeRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
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
    fun signUpUser(
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

    suspend fun getLastLocation(
        fusedLocationClient: FusedLocationProviderClient,
        context: Context
    ) {
        if (ContextCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            try {
                val location = fusedLocationClient.lastLocation.await() // Use await() to make it suspendable
                location?.let {
                    val userLocation = LatLng(it.latitude, it.longitude)
                    Log.d("Location", "User is at: $userLocation")
                } ?: run {
                    Toast.makeText(context, "Location not available, make sure GPS is enabled", Toast.LENGTH_SHORT).show()
                }
            } catch (e: SecurityException) {
                e.printStackTrace()
                Toast.makeText(context, "Location permission is required to access location", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "Failed to get location", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Location permission is not granted", Toast.LENGTH_SHORT).show()
        }
    }




}