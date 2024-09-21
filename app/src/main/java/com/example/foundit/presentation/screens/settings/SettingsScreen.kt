package com.example.foundit.presentation.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Account Center", forwardNavigation = NavRoutes.ACCOUNT_CENTER, navController = navController)
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Language", forwardNavigation = NavRoutes.LANGUAGE, navController = navController)
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Appearance", forwardNavigation = NavRoutes.APPEARANCE, navController = navController)
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Security", forwardNavigation = NavRoutes.SECURITY, navController = navController)
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Help and Support", forwardNavigation = NavRoutes.HELP_AND_SUPPORT, navController = navController)
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Feedback", forwardNavigation = NavRoutes.FEEDBACK, navController = navController)
            SettingsOptionCard(modifier = modifier, settingsOptionName = "About", forwardNavigation = NavRoutes.ABOUT, navController = navController)


            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(bottom = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,

            ){
                Card(
                    modifier = modifier
                        .clickable {
                            navController.navigate(NavRoutes.LOG_OUT)
                        }
                        .fillMaxWidth()
                        .height(64.dp),
                    shape = RectangleShape,
                ){
                    Row(
                        modifier = modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ){
                        Text(
                            text = "Log Out",
                            color = Color.Red,
                            fontSize = 18.sp,
                            textDecoration = TextDecoration.Underline
                        )
                    }

                }
            }
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
