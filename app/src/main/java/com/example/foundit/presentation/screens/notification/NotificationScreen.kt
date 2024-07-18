package com.example.foundit.presentation.screens.notification

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
import com.example.foundit.presentation.common.TheTopAppBar
import com.example.foundit.presentation.data.navigation.NavRoutes
import com.example.foundit.presentation.screens.notification.components.NotificationCard

@Composable
fun NotificationScreen(
    modifier: Modifier,
    navController: NavController
) {
    Scaffold(
        topBar ={ TheTopAppBar(title = "Notifications", navController = navController, backRoute = NavRoutes.HOME) }
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