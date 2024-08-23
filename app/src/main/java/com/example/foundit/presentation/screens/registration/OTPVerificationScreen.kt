package com.example.foundit.presentation.screens.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.navigation.NavHostController
import com.example.foundit.presentation.common.TheTopAppBar
import kotlinx.coroutines.delay

@Composable
fun OTPVerificationScreen(
    navController: NavHostController
) {
    var otp by remember { mutableStateOf("") }
    var timer by remember { mutableIntStateOf(10) }
    var isTimerRunning by remember { mutableStateOf(false) }
    var displayMsg by remember { mutableStateOf(false) }
    val focusRequesters = List(6) { FocusRequester() }

    LaunchedEffect(isTimerRunning) {
        while (isTimerRunning && timer > 0) {
            delay(1000)
            timer -= 1
        }
        if (timer == 0) {
            isTimerRunning = false
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TheTopAppBar(title = "", navController = navController)},
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "OTP Verification",
                style = MaterialTheme.typography.headlineMedium.copy(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "We have sent you an OTP to your email t***39@gmail.com",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(6) { index ->
                    OutlinedTextField(
                        value = if (index < otp.length) otp[index].toString() else "",
                        onValueChange = { value ->
                            if (value.length <= 1 && value.all { it.isDigit() }) {
                                otp = otp.take(index) + value + otp.drop(index + 1)
                                if (value.isNotEmpty() && index < 5) {
                                    focusRequesters[index + 1].requestFocus()
                                }
                            }
                        },
                        textStyle = MaterialTheme.typography.bodySmall.copy(fontSize = 18.sp, textAlign = TextAlign.Center),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        modifier = Modifier
                            .weight(1f)
                            .background(Color.White)
                            .focusRequester(focusRequesters[index])
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    // Handle OTP verification logic
                    isTimerRunning = true
                    displayMsg = true
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(48.dp),
                enabled = otp.length == 6
            ) {
                Text(text = "Verify", color = Color.White, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (displayMsg) {
                Text(
                    text = if (timer > 0) "Resend OTP in $timer seconds ..." else "Resend OTP",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp, color = if (timer > 0) Color.Gray else Color.Blue),
                    modifier = if (timer == 0) Modifier.clickable {
                        // Handle resend OTP logic
                        focusRequesters[0].requestFocus()
                        timer = 10
                        isTimerRunning = true
                        otp = ""
                    } else Modifier,
                    textAlign = TextAlign.Center
                )
            }
/*
            Text(
                text = "Edit Email",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp, color = Color.Blue),
                modifier = Modifier.clickable {
                    navController.popBackStack()
                },
                textAlign = TextAlign.Center
            )
*/
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewOTPVerificationScreen() {
    OTPVerificationScreen(navController = NavHostController(LocalContext.current))
}
