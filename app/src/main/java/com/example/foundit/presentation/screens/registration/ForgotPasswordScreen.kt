package com.example.foundit.presentation.screens.registration

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.foundit.presentation.data.navigation.NavRoutes
import com.example.foundit.ui.theme.MainGreen

@Composable
fun ForgotPasswordScreen(
    modifier: Modifier,
    navController: NavHostController
) {
    var email by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(true) }
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
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

                    Spacer(modifier = modifier.height(20.dp))
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
                                navController.navigate(NavRoutes.LOGIN)
                                Toast.makeText(context, "mail sent!", Toast.LENGTH_LONG).show()
                            },
                            colors = ButtonColors(
                                containerColor = MainGreen,
                                contentColor = MaterialTheme.colorScheme.surface,
                                disabledContainerColor = Color.Gray,
                                disabledContentColor = MaterialTheme.colorScheme.onSurface,

                            ),
                            elevation = ButtonDefaults.elevatedButtonElevation(10.dp),
                            enabled = email.isNotEmpty() && isEmailValid

                        ) {
                            Text(
                                text = "Send Reset Link",
                                color = MaterialTheme.colorScheme.surface,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back Arrow",
                            tint = MainGreen,
                            modifier = modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Back to Login",
                            fontSize = 16.sp,
                            color = MainGreen,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.clickable {
                                navController.navigate(NavRoutes.LOGIN)
                            },
                            textAlign = TextAlign.Center
                        )
                    }

                }
            }
        }


    }
}


@Preview (showBackground = true, showSystemUi = true)
@Composable
fun PreviewForgotPasswordScreen(){
    ForgotPasswordScreen(
        modifier = Modifier,
        navController = NavHostController(LocalContext.current)
    )
}