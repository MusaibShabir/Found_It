package com.example.foundit.presentation.screens.settings.components.clickable

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
fun AboutScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar ={
            TheTopAppBar(title = "About", navController = navController)
        }
    ) {innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding),
        ) {
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Version", forwardNavigation = NavRoutes.VERSION, navController = navController)
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Privacy Policy", forwardNavigation = NavRoutes.PRIVACY_POLICY, navController = navController)
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Terms of Service", forwardNavigation = NavRoutes.TERMS_OF_SERVICE, navController = navController)
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Acknowledgments", forwardNavigation = NavRoutes.ACKNOWLEDGMENTS, navController = navController)
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Developer Information", forwardNavigation = NavRoutes.DEVELOPER_INFO, navController = navController)
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Follow us", forwardNavigation = NavRoutes.FOLLOW_US, navController = navController)
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Follow us", forwardNavigation = NavRoutes.FOLLOW_US, navController = navController)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AboutScreenPreview(){
    AboutScreen(navController = NavHostController(LocalContext.current))
}