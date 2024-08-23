package com.example.foundit.presentation.screens.settings.components.clickable

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
import androidx.navigation.NavHostController
import com.example.foundit.presentation.common.TheTopAppBar

@Composable
fun ChangeEmailScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
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
                    currentEmail = it
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
            }

            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Button(
                    onClick = {
                        // Handle email change logic
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = currentEmail.isNotEmpty()
                            && newEmail.isNotEmpty()
                            && currentEmail != newEmail
                            && !currentEmailError
                            && !newEmailError
                ) {
                    Text("Change Email")
                }
                if (newEmail == currentEmail && currentEmail.isNotEmpty()) {
                    Text(
                        text = "New email cannot be the same as the current email",
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
