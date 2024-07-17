package com.example.foundit.presentation.screens.registration

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Man
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foundit.presentation.screens.registration.components.ClickableTextToWebpage
import com.example.foundit.presentation.screens.registration.components.ContinueWithGoogleCard
import com.example.foundit.presentation.screens.registration.components.OrDivider


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(modifier: Modifier) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ){
            Text(
                text = "Create Account",
                style = MaterialTheme.typography.headlineLarge
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxWidth()
                .height(intrinsicSize = IntrinsicSize.Min),
        ) {
            // First Name
            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp),
                value = firstName,
                onValueChange = {firstName = it},
                label = { Text("First Name") },
                leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = "Person icon") },
                placeholder = { Text("Enter Your First Name", fontStyle = FontStyle.Italic) },
                shape = MaterialTheme.shapes.medium

            )
            // Last Name
            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp),
                value = lastName,
                onValueChange = {lastName = it},
                label = { Text("Last Name") },
                placeholder = { Text("Enter Your Last Name", fontStyle = FontStyle.Italic) },
                leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = "Person icon") },
                shape = MaterialTheme.shapes.medium

            )

            //Gender
            var expanded by remember { mutableStateOf(false) }
            val options = listOf("Male", "Female", "Other")
            ExposedDropdownMenuBox(
                modifier = modifier.fillMaxWidth(),
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    readOnly = true,
                    value = gender,
                    onValueChange = {},
                    label = { Text("Gender") },
                    leadingIcon = { Icon(Icons.Outlined.Man, contentDescription = "Man icon") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    placeholder = { Text("Select Your Gender", fontStyle = FontStyle.Italic) },
                    shape = MaterialTheme.shapes.medium,
                    modifier = modifier
                        .fillMaxWidth()
                        .menuAnchor()
                        .padding(bottom = 18.dp)
                )

                ExposedDropdownMenu(

                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                gender = selectionOption
                                expanded = false
                            }
                        )
                    }
                }
            }

            //Email
            var isEmailValid by remember { mutableStateOf(true) }
            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = if (!isEmailValid && email.isNotBlank()) 10.dp else 0.dp),
                value = email,
                onValueChange = {
                    email = it
                    isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()},
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = "Email icon") },
                placeholder = { Text("Enter Your Email", fontStyle = FontStyle.Italic) },
                shape = MaterialTheme.shapes.medium,
                isError = !isEmailValid,
                supportingText = {
                    if (!isEmailValid && email.isNotBlank()) {
                        Text("Invalid email address", color = MaterialTheme.colorScheme.error)
                    }
                },


            )
            
            //Password
            var passwordVisible by remember { mutableStateOf(false) }
            var isPasswordValid by remember { mutableStateOf(true) }
            val icon = if (!passwordVisible) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility
            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = if (!isPasswordValid && password.isNotBlank()) 10.dp else 0.dp),
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
                isError = !isPasswordValid,
                supportingText = {
                    if (!isPasswordValid && password.isNotBlank()) {
                        Text("Password must be at least 8 characters", color = MaterialTheme.colorScheme.error)
                    }
                }
            )

        }// Text Field Column Scope

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            ElevatedButton(
                modifier = modifier
                    .width(200.dp)
                    .height(52.dp),
                onClick = { /*TODO*/ },
                colors = ButtonColors(
                    containerColor = Color.Blue,
                    contentColor = MaterialTheme.colorScheme.surface,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = MaterialTheme.colorScheme.onSurface
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(20.dp)

            ) {
                Text(
                    text = "SIGN UP",
                    color = MaterialTheme.colorScheme.surface,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                )
            }
        }// Button Row

        OrDivider(modifier = modifier)
        ContinueWithGoogleCard(modifier = modifier)

        Column(modifier = modifier
            .fillMaxSize()
            .padding(bottom = 20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "By continuing, I agree with the")
            }

            Spacer(modifier = modifier.height(10.dp))
            Row(
                    modifier = modifier
                        .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
            ) {
                ClickableTextToWebpage(
                    text = "Terms of Service",
                    url = "https://www.google.com",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = "&")

                ClickableTextToWebpage(
                    text = "Privacy Policy",
                    url = "https://www.google.com",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

    }
}




@Composable
@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_6_pro")
fun PreviewSignUpScreen() {
    SignUpScreen(modifier = Modifier)
}

