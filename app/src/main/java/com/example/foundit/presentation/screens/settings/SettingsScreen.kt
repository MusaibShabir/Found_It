package com.example.foundit.presentation.screens.settings

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.foundit.presentation.common.TheTopAppBar
import com.example.foundit.presentation.data.navigation.NavRoutes
import com.example.foundit.presentation.screens.settings.components.SettingsOptionCard
import com.example.foundit.presentation.screens.settings.components.clickable.LogoutViewModel
import com.example.foundit.ui.theme.RobotFamily

@Composable
fun SettingsScreen(
    modifier: Modifier,
    navController: NavHostController
) {
    val viewModel: LogoutViewModel = hiltViewModel()
    val context = LocalContext.current
    var showAlertDialogBox by remember { mutableStateOf(false) }


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
                OutlinedCard(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    shape = RectangleShape,
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.background,
                    ),
                    border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.onBackground)

                ){
                    Row(
                        modifier = modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ){
                        TextButton(
                            onClick = { showAlertDialogBox = !showAlertDialogBox },
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = MaterialTheme.colorScheme.error)
                        ) {
                            Text(
                            text = "Log Out",
                            fontSize = 18.sp,
                            )
                        }

                    }

                }
            }



            if (showAlertDialogBox) {
                AlertDialog(
                    modifier = modifier,
                    onDismissRequest = { showAlertDialogBox = !showAlertDialogBox },
                    title = { Text(
                        text = "Are you sure you want to log out?",
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
                                viewModel.logout { isSuccess ->
                                    if (isSuccess) {
                                        Toast.makeText(context, "Logged out successfully" , Toast.LENGTH_SHORT).show()
                                        navController.navigate(NavRoutes.SPLASH) {
                                            popUpTo(navController.graph.startDestinationId) {
                                                inclusive = true
                                            }
                                            launchSingleTop = true
                                        }
                                    } else {
                                        Toast.makeText(context, "Logout failed. Please try again." , Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }) {
                            Text("Yes")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { showAlertDialogBox = false },
                            modifier = modifier.padding(horizontal = 10.dp)
                        ) {
                            Text("No")
                        }

                    }
                )
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
