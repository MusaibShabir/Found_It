package com.example.foundit.presentation.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.foundit.presentation.common.TheTopAppBar
import com.example.foundit.presentation.data.navigation.NavRoutes
import com.example.foundit.presentation.screens.settings.components.SettingsOptionCard

@Composable
fun SettingsScreen(
    modifier: Modifier,
    navController: NavHostController
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar ={
            TheTopAppBar(title = "Settings", navController = navController, backRoute = NavRoutes.PROFILE)
        }
    ) {innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding),
        ) {
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Account Center", forwardNavigation = NavRoutes.PROFILE, navController = navController)
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Language", forwardNavigation = NavRoutes.LANGUAGE, navController = navController)
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Appearance", forwardNavigation = NavRoutes.APPEARANCE, navController = navController)
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Security", forwardNavigation = NavRoutes.SECURITY, navController = navController)
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Help and Support", forwardNavigation = NavRoutes.HELP_AND_SUPPORT, navController = navController)
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Feedback", forwardNavigation = NavRoutes.FEEDBACK, navController = navController)
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Share", forwardNavigation = NavRoutes.SHARE, navController = navController)
            SettingsOptionCard(modifier = modifier, settingsOptionName = "About", forwardNavigation = NavRoutes.ABOUT, navController = navController)
        }
    }
}

@Preview (showBackground = true, showSystemUi = false)
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen(
        modifier = Modifier,
        navController = NavHostController(LocalContext.current)
    )
}
