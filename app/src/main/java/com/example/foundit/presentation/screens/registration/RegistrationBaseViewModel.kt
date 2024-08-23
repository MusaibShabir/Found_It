package com.example.foundit.presentation.screens.registration

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

open class RegistrationBaseViewModel: ViewModel() {

    //Email
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    fun onEmailChange(value: String) {
        _email.value = value
    }

    fun validateEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }



    //Password
    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun onPasswordChange(value: String) {
        _password.value = value
    }

    fun validatePassword(password: String): Boolean {
        return password.length >= 8
    }
}