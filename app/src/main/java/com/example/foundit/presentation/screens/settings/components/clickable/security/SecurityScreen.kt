package com.example.foundit.presentation.screens.settings.components.clickable.security

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
fun SecurityScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar ={
            TheTopAppBar(title = "Security", navController = navController)
        }
    ) {innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding),
        ) {
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Change Password", forwardNavigation = NavRoutes.CHANGE_PASSWORD, navController = navController)
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Change Phone Number", forwardNavigation = NavRoutes.CHANGE_PHONE_NUMBER, navController = navController)
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Change Email", forwardNavigation = NavRoutes.CHANGE_EMAIL, navController = navController)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSecurityScreen(){
    SecurityScreen(navController = NavHostController(LocalContext.current))
}