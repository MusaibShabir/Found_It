package com.example.foundit.presentation.screens.settings.components.clickable.account_center

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foundit.presentation.common.TheTopAppBar

@Composable
fun ChangePasswordScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var currentPasswordError by remember { mutableStateOf(false) }
    var newPasswordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }
    val icon = if (!passwordVisible) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TheTopAppBar(title = "Change Password", navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = currentPassword,
                onValueChange = {
                    currentPassword = it.filter { char -> !char.isWhitespace() }
                    currentPasswordError = it.length < 8
                },
                label = { Text("Current Password") },
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
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(icon, contentDescription = if (passwordVisible) "Hide password" else "Show password")
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                isError = currentPasswordError
            )
            if (currentPasswordError) {
                Text(
                    text = "Password must be at least 8 characters long",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            TextField(
                value = newPassword,
                onValueChange = {
                    newPassword = it.filter { char -> !char.isWhitespace() }
                    newPasswordError = it.length < 8
                },
                label = { Text("New Password") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent
                ),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(icon, contentDescription = if (passwordVisible) "Hide password" else "Show password")
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                isError = newPasswordError
            )
            if (newPasswordError) {
                Text(
                    text = "Password must be at least 8 characters long",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            TextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it.filter { char -> !char.isWhitespace() }
                    confirmPasswordError = it != newPassword
                },
                label = { Text("Confirm Password") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent
                ),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(icon, contentDescription = if (passwordVisible) "Hide password" else "Show password")
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                isError = confirmPasswordError
            )
            if (confirmPasswordError) {
                Text(
                    text = "Passwords do not match",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        // Handle password change logic
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    enabled = currentPassword.isNotBlank()
                            && newPassword.isNotBlank()
                            && confirmPassword.isNotBlank()
                            && !currentPasswordError
                            && !newPasswordError
                            && !confirmPasswordError
                            && newPassword == confirmPassword
                            && newPassword != currentPassword
                ) {
                    Text("Change Password")
                }
                if (newPassword == currentPassword && currentPassword.isNotEmpty()) {
                    Text(
                        text = "New password cannot be the same as current password",
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
fun PreviewChangePasswordScreen() {
    ChangePasswordScreen(navController = NavHostController(LocalContext.current))
}