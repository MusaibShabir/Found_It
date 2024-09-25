package com.example.foundit.presentation.screens.input.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import com.example.foundit.ui.theme.MainGreen
import com.example.foundit.ui.theme.RobotFamily

@Composable
fun AreYouSureToCancelAlertBox(
    modifier: Modifier,
    onDismissRequest: () -> Unit,
    navController: NavController
) {
    AlertDialog(
        modifier = modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        onDismissRequest = onDismissRequest,
        title = { Text(
            text = "Are you Sure?",
            textAlign = TextAlign.Start,
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
            fontFamily = RobotFamily
        ) },
        text = { Text(
            text = "This action cannot be undone.",
            fontSize = 16.sp
        )},
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
                colors = ButtonDefaults.buttonColors(containerColor = MainGreen),
                modifier = modifier.padding(horizontal = 10.dp)
            ) {
                Text("No")
            }

        }
    )
}