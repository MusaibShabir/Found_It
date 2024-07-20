package com.example.foundit.presentation.screens.registration.signup

import androidx.lifecycle.viewModelScope
import com.example.foundit.presentation.screens.registration.RegistrationBaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel: RegistrationBaseViewModel() {

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
}