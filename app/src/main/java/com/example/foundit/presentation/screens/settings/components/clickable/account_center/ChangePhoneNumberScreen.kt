package com.example.foundit.presentation.screens.settings.components.clickable.account_center

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foundit.presentation.common.TheTopAppBar

@Composable
fun ChangePhoneNumberScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    var currentPhoneNumber by remember { mutableStateOf("") }
    var newPhoneNumber by remember { mutableStateOf("") }
    var currentPhoneNumberError by remember { mutableStateOf(false) }
    var newPhoneNumberError by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TheTopAppBar(title = "Change Phone Number", navController = navController)
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
                value = currentPhoneNumber,
                onValueChange = {
                    if (it.length <= 10) {
                        currentPhoneNumber = it.filter { char -> char.isDigit() }
                        currentPhoneNumberError = currentPhoneNumber.length != 10 && currentPhoneNumber.isNotEmpty()
                    }
                },
                label = { Text("Current Phone Number") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent
                ),
                isError = currentPhoneNumberError
            )
            if (currentPhoneNumberError) {
                Text(
                    text = "Phone number must be 10 digits",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            TextField(
                value = newPhoneNumber,
                onValueChange = {
                    if (it.length <= 10) {
                        newPhoneNumber = it.filter { char -> char.isDigit() }
                        newPhoneNumberError = newPhoneNumber.length != 10 && newPhoneNumber.isNotEmpty()
                    }
                },
                label = { Text("New Phone Number") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent
                ),
                isError = newPhoneNumberError
            )
            if (newPhoneNumberError) {
                Text(
                    text = "Phone number must be 10 digits",
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
                        // Handle phone number change logic
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    enabled = currentPhoneNumber.isNotEmpty()
                            && newPhoneNumber.isNotEmpty()
                            && currentPhoneNumber != newPhoneNumber
                            && !currentPhoneNumberError
                            && !newPhoneNumberError
                ) {
                    Text("Change Phone Number")
                }
                if (newPhoneNumber == currentPhoneNumber && currentPhoneNumber.isNotEmpty() ) {
                    Text(
                        text = "Current and new phone numbers must be different",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewChangePhoneNumberScreen() {
    ChangePhoneNumberScreen(navController = NavHostController(LocalContext.current))
}
