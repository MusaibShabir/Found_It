package com.example.foundit.presentation.screens.settings.components.clickable.account_center

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.foundit.presentation.common.TheTopAppBar
import com.example.foundit.presentation.data.navigation.NavRoutes
import com.example.foundit.presentation.screens.settings.components.clickable.ChangeEmailViewModel
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.google.firebase.auth.FirebaseAuthUserCollisionException

@Composable
fun ChangeEmailScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val viewModel: ChangeEmailViewModel = hiltViewModel()
    val context = LocalContext.current

    val isGoogleProvide = viewModel.isGoogleProvider

    var currentEmail by remember { mutableStateOf("") }
    var newEmail by remember { mutableStateOf("") }
    var currentEmailError by remember { mutableStateOf(false) }
    var newEmailError by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar ={
            TheTopAppBar(title = "Change Email", navController = navController)
        }
    ) {innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = currentEmail,
                onValueChange = {
                    currentEmail = it.filter { char -> char.isLetterOrDigit() || char == '@' || char == '.' }
                    currentEmailError = !isValidEmail(it)
                },
                label = { Text("Current Email") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent
                ),
                isError = currentEmailError
            )
            if (currentEmailError) {
                Text(
                    text = "Invalid email format",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }else if(currentEmail != viewModel.email && currentEmail.isNotEmpty() ){
                Text(
                    text = "Incorrect current email",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            TextField(
                value = newEmail,
                onValueChange = {
                    newEmail = it
                    newEmailError = !isValidEmail(it)
                },
                label = { Text("New Email") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent
                ),
                isError = newEmailError
            )
            if (newEmailError) {
                Text(
                    text = "Invalid email format",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }else if (newEmail == currentEmail && currentEmail.isNotEmpty()) {
            Text(
                text = "New email cannot be the same as the current email",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Button(
                    onClick = {
                        viewModel.updateEmail(newEmail) { isSuccess, e ->
                            if (isSuccess) {
                                Toast.makeText(context, "Verification mail sent. Please verify your email.", Toast.LENGTH_SHORT).show()
                                navController.navigate(NavRoutes.SPLASH)
                            } else {
                                val errorMessage = when (e) {
                                    is FirebaseNetworkException -> "Network issue. Please check your connection and try again."
                                    is FirebaseAuthInvalidCredentialsException -> "Invalid email. Please enter a valid email address."
                                    is FirebaseAuthUserCollisionException -> "Email already in use. Please use a different email."
                                    is FirebaseAuthRecentLoginRequiredException -> "Please log out and re-authenticate before changing your email."
                                    is FirebaseAuthInvalidUserException -> "User account no longer exists or has been disabled."
                                    else -> "An unexpected error occurred: ${e?.message ?: "Unknown error"}"
                                }
                                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = currentEmail.isNotEmpty()
                            && newEmail.isNotEmpty()
                            && currentEmail != newEmail
                            && !currentEmailError
                            && !newEmailError
                            && !isGoogleProvide
                ) {
                    Text("Change Email")
                }
                if (isGoogleProvide) {
                    Text(
                        text = "You are logged in with Google",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun PreviewChangeEmailScreen() {
    ChangeEmailScreen(navController = NavHostController(LocalContext.current))
}

private fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
