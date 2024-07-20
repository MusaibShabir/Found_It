package com.example.foundit.presentation.screens.registration.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foundit.presentation.screens.registration.components.ClickableTextToWebpage
import com.example.foundit.presentation.screens.registration.components.ContinueWithGoogleCard
import com.example.foundit.presentation.screens.registration.components.OrDivider

@Composable
fun LoginScreen(
    modifier: Modifier,
    viewModel: LoginViewModel = viewModel()

) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row (
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 120.dp, bottom = 30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "LOGIN",
                fontSize = 34.sp
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
            var hasEmailInteracted by remember { mutableStateOf(false) }
            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth(),
                value = email,
                onValueChange = {
                    hasEmailInteracted = true
                    viewModel.onEmailChange(it)
                    },
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = "Email icon") },
                trailingIcon = { if (!viewModel.validateEmail(email) && email.isNotBlank()) {
                    Icon(Icons.Filled.Error, contentDescription = "Email icon") }
                },
                placeholder = { Text("Enter Your Email", fontStyle = FontStyle.Italic) },
                shape = MaterialTheme.shapes.medium,
                isError = hasEmailInteracted && !viewModel.validateEmail(email),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = Color.Blue,
                    cursorColor = Color.Gray,
                    focusedBorderColor = Color.Blue
                ),
                supportingText = {
                    if (hasEmailInteracted && !viewModel.validateEmail(email) && email.isNotBlank()) {
                        Text("Invalid email address", color = MaterialTheme.colorScheme.error)
                    }
                },
                )

            //Password
            var passwordVisible by remember { mutableStateOf(false) }
            var hasPasswordInteracted by remember { mutableStateOf(false) }
            val icon = if (!passwordVisible) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility
            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth(),
                value = password,
                onValueChange = {
                    hasPasswordInteracted = true
                    viewModel.onPasswordChange(it)
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
                isError = hasPasswordInteracted && !viewModel.validatePassword(password),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = Color.Blue,
                    cursorColor = Color.Gray,
                    focusedBorderColor = Color.Blue,
                    errorTrailingIconColor = MaterialTheme.colorScheme.onSurface
                ),
                supportingText = {
                    if (hasPasswordInteracted && !viewModel.validatePassword(password) && password.isNotBlank()) {
                        Text("Password must be at least 8 characters", color = MaterialTheme.colorScheme.error)
                    }
                }
            )

            
        }// TextFields Column Scope

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top
        ) {
            ClickableTextToWebpage(text = "Forgot Password", url = "https://www.google.com") // Later to be changed to from url -> screen
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
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
                elevation = ButtonDefaults.elevatedButtonElevation(10.dp)

            ) {
                Text(
                    text = "LOGIN",
                    color = MaterialTheme.colorScheme.surface,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                )
            }
        }// Button Row Scope
        OrDivider(modifier = modifier)
        ContinueWithGoogleCard(modifier = modifier, colorScheme = 2)
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_6_pro")
fun PreviewLoginScreen() {
    LoginScreen(modifier = Modifier)
}
