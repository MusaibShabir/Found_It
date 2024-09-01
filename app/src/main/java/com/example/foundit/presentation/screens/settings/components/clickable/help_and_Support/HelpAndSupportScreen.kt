package com.example.foundit.presentation.screens.settings.components.clickable.help_and_Support

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
fun HelpAndSupportScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar ={
            TheTopAppBar(title = "Help and Support", navController = navController)
        }
    ) {innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding),
        ) {
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Report a Problem", forwardNavigation = NavRoutes.REPORT_A_BUG, navController = navController)
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Contact Support", forwardNavigation = NavRoutes.CONTACT_SUPPORT, navController = navController)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHelpAndSupportScreen(){
    HelpAndSupportScreen(navController = NavHostController(LocalContext.current))
}