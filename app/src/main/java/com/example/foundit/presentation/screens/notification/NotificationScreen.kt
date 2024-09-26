package com.example.foundit.presentation.screens.notification

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.foundit.presentation.common.TheTopAppBar
import com.example.foundit.presentation.screens.notification.components.NotificationCard

@Composable
fun NotificationScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: NotificationBaseViewModel
) {
    Scaffold(
        topBar ={ TheTopAppBar(title = "Notifications", navController = navController, isNavigationIconVisible = false) }
    ) {innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            NotificationCard(modifier = modifier, viewModel = viewModel)
        }
    }

    BackHandler(
        enabled = true,
    ) {

    }
}

/*
@Composable
@Preview(showBackground = true, showSystemUi = false)
fun PreviewNotificationScreen() {
    NotificationScreen(
        modifier = Modifier,
        navController = NavController(LocalContext.current)
    )
}

 */