package com.example.foundit.presentation.screens.registration.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.foundit.presentation.data.navigation.NavRoutes
import com.example.foundit.presentation.screens.registration.components.ClickableTextToNavigationRoute
import com.example.foundit.presentation.screens.registration.components.google.ContinueWithGoogleCard
import com.example.foundit.presentation.screens.registration.components.OrDivider
import com.example.foundit.presentation.screens.registration.components.google.ContinueWithGoogleViewModel
import com.example.foundit.ui.theme.MainGreen
import com.example.foundit.ui.theme.Righteous
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

@Composable
fun LoginScreen(
    modifier: Modifier,
    loginViewModel: LoginViewModel,
    continueWithGoogleViewModel: ContinueWithGoogleViewModel,
    navController: NavController
) {
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // For Validation
    var isEmailValid by remember { mutableStateOf(true) }
    var isPasswordValid by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row (
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 120.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Login",
                fontSize = 36.sp,
                fontFamily = Righteous,
                fontWeight = FontWeight.Normal
            )

        }// Text Row Scope

        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            //Email
            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth(),
                value = email,
                onValueChange = {
                    email = it//.filter { char -> char.isLetterOrDigit() || char == '@' || char == '.' }
                    isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()},
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = "Email icon") },
                trailingIcon = { if (!isEmailValid && email.isNotBlank()) {
                    Icon(Icons.Filled.Error, contentDescription = "Email icon") }
                },
                placeholder = { Text("Enter Your Email", fontStyle = FontStyle.Italic) },
                shape = MaterialTheme.shapes.medium,
                singleLine = true,
                isError = !isEmailValid,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                    errorContainerColor = MaterialTheme.colorScheme.onPrimary,
                    focusedLabelColor = MaterialTheme.colorScheme.onSurface,
                    cursorColor = MainGreen,
                    focusedBorderColor = MainGreen,
                    selectionColors = TextSelectionColors(
                        handleColor = MainGreen,
                        backgroundColor = MainGreen,
                    ),
                ),
                supportingText = {
                    if (!isEmailValid && email.isNotBlank()) {
                        Text("Invalid email address", color = MaterialTheme.colorScheme.error)
                    }
                },
            )

            //Password
            var passwordVisible by remember { mutableStateOf(false) }
            val icon = if (!passwordVisible) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility
            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth(),
                value = password,
                onValueChange = {
                    password = it
                    isPasswordValid = it.length >= 8
                },
                label = { Text("Password") },
                leadingIcon = { Icon(Icons.Outlined.Lock, contentDescription = "Lock icon") },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(icon, contentDescription = if (passwordVisible) "Hide password" else "Show password")
                    }
                },
                placeholder ={ Text("Enter Your Password", fontStyle = FontStyle.Italic) },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                shape = MaterialTheme.shapes.medium,
                singleLine = true,
                isError = !isPasswordValid,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                    errorContainerColor = MaterialTheme.colorScheme.onPrimary,
                    focusedLabelColor = MaterialTheme.colorScheme.onSurface,
                    cursorColor = MainGreen,
                    focusedBorderColor = MainGreen,
                    selectionColors = TextSelectionColors(
                        handleColor = MainGreen,
                        backgroundColor = MainGreen,
                    ),
                ),
                supportingText = {
                    if (!isPasswordValid && password.isNotBlank()) {
                        Text("Password must be at least 8 characters", color = MaterialTheme.colorScheme.error)
                    }
                }
            )


        }// TextFields Column Scope

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 15.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top
        ) {

            ClickableTextToNavigationRoute(
                text = "Forgot Password",
                navRoute = NavRoutes.FORGOT_PASSWORD,
                modifier = modifier,
                navController = navController
            )
        }

        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ElevatedButton(
                modifier = modifier
                    .width(200.dp)
                    .height(52.dp),
                onClick = {
                    loginViewModel.login(email, password) { isSuccess, e ->
                        if (isSuccess) {
                            Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                            navController.navigate(NavRoutes.HOME)
                        } else {
                            val errorMessage = when (e) {
                                is FirebaseAuthInvalidCredentialsException -> "Invalid email or password. Please try again."
                                is FirebaseAuthInvalidUserException -> "Your account has been disabled."
                                is FirebaseNetworkException -> "Network issue: Please check your connection and try again."
                                else -> "An error occurred. Please try again."
                            }
                            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                colors = ButtonColors(
                    containerColor = MainGreen,
                    contentColor = MaterialTheme.colorScheme.surface,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = MaterialTheme.colorScheme.onSurface
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(10.dp),
                enabled = email.isNotEmpty()
                        && password.isNotEmpty()
                        && isEmailValid
                        && isPasswordValid

            ) {
                Text(
                    text = "LOGIN",
                    color = MaterialTheme.colorScheme.surface,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                )
            }
        } // Button Row Scope

        OrDivider(modifier = modifier)

        ContinueWithGoogleCard(
            modifier = modifier,
            //colorScheme = 2,
            continueWithGoogleViewModel = continueWithGoogleViewModel
        ){ credential ->
            loginViewModel.onSignInWithGoogle(credential) { isSuccess ->
                if (isSuccess) {
                    Log.d("SignUp", "User created successfully")
                    navController.navigate(NavRoutes.HOME)
                } else {
                    Log.d("SignUp", "Authentication failed")
                }
            }
        }

        Spacer(modifier = modifier.height(50.dp))

        //Don't have an account ?
        Row (
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Don't have an account ?",
                fontWeight = FontWeight.Medium
            )

            ClickableTextToNavigationRoute(
                text = "Sign Up",
                navRoute = NavRoutes.SIGN_UP,
                modifier = modifier.padding(start = 8.dp),
                navController = navController
            )


        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_6_pro")
fun PreviewLoginScreen() {
    //LoginScreen(modifier = Modifier)
}