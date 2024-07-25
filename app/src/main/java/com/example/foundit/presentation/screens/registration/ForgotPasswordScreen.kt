package com.example.foundit.presentation.screens.registration

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ForgotPasswordScreen(
    navController: NavHostController
) {
    var email by remember { mutableStateOf("") }
    var emailSent by remember { mutableStateOf(false) }
    var errorOccurred by remember { mutableStateOf(false) }
    var isEmailValid by remember { mutableStateOf(true) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ){
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors( containerColor = Color.White ),
                elevation = CardDefaults.cardElevation(4.dp)
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 30.dp)
                ) {
                    Text(
                        text = "Forgot password",
                        style = MaterialTheme.typography.headlineMedium.copy(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    
                    Text(
                        text = "Enter your email address and we will send you a link to reset your password.",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    /*
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Enter your email address") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.LightGray,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            //textColor = Color.Black
                        )
                    )
                    */

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = email,
                        onValueChange = {
                            email = it
                            isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()},
                        label = { Text("Email") },
                        leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = "Email icon") },
                        trailingIcon = { if (!isEmailValid && email.isNotBlank()) {
                            Icon(Icons.Filled.Error, contentDescription = "Email icon") }
                        },
                        placeholder = { Text("Enter Your Email", fontStyle = FontStyle.Italic) },
                        shape = MaterialTheme.shapes.medium,
                        isError = !isEmailValid,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedLabelColor = Color.Blue,
                            cursorColor = Color.Gray,
                            focusedBorderColor = Color.Blue
                        ),
                        supportingText = {
                            if (!isEmailValid && email.isNotBlank()) {
                                Text("Invalid email address", color = MaterialTheme.colorScheme.error)
                            }
                        },
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            // Handle sending reset link logic
                            emailSent = true
                            errorOccurred = true
                            if (emailSent && !errorOccurred) {
                                // Navigate back to the login screen if email is sent
                                navController.popBackStack()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        enabled = isEmailValid && email.isNotBlank()
                    ) {
                        Text(text = "Send Reset Link", color = Color.White, fontSize = 18.sp)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    if (emailSent && errorOccurred) {
                        Text(
                            text = "error occurred Try Again",
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Back to Login",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp, color = Color.Blue),
                        modifier = Modifier.clickable {
                            // Navigate back to the login screen
                            navController.popBackStack()
                        },
                        textAlign = TextAlign.Center
                    )
                }
            }
        }


    }
}


@Preview (showBackground = true, showSystemUi = true)
@Composable
fun PreviewForgotPasswordScreen(){
    ForgotPasswordScreen(navController = NavHostController(LocalContext.current))
}