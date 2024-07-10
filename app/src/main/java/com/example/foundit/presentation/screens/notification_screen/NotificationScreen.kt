package com.example.foundit.presentation.screens.notification_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.foundit.presentation.screens.notification_screen.components.NotificationCard
import com.example.foundit.presentation.screens.notification_screen.components.NotificationsTopAppBar

@Composable
fun NotificationScreen(
    modifier: Modifier,
    navController: NavController
) {
    Scaffold(
        topBar ={ NotificationsTopAppBar(title = "Notifications", navController = navController) }
    ) {innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            NotificationCard()
        }
    }
}
@Composable
@Preview(showBackground = true, showSystemUi = false)
fun PreviewNotificationScreen() {
    NotificationScreen(
        modifier = Modifier,
        navController = NavController(LocalContext.current)
    )
}