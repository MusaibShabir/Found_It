package com.example.foundit.presentation.screens.input.common.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.foundit.presentation.data.navigation.NavRoutes
import com.example.foundit.ui.theme.RobotFamily

@Composable
fun AreYouSureToCancelAlertBox(
    modifier: Modifier,
    onDismissRequest: () -> Unit,
    navController: NavController
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        title = { Text(
            text = "Are you sure you want to cancel?",
            textAlign = TextAlign.Start,
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
            fontFamily = RobotFamily
        ) },
        confirmButton = {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red.copy(alpha = 0.7f)),
                onClick = {
                    onDismissRequest()
                    navController.navigate(NavRoutes.HOME)
                }) {
                Text("Yes")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismissRequest,
                modifier = modifier.padding(horizontal = 10.dp)
            ) {
                Text("No")
            }

        }
    )
}