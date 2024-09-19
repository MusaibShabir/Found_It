package com.example.foundit.presentation.screens.settings.components.clickable.about

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.foundit.presentation.common.TheTopAppBar
import com.example.foundit.presentation.data.navigation.NavRoutes
import com.example.foundit.presentation.screens.settings.components.clickable.LogoutViewModel

@Composable
fun LogoutScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    val viewModel: LogoutViewModel = hiltViewModel()
    val context = LocalContext.current

    Scaffold(
        modifier= modifier,
        topBar ={
            TheTopAppBar(title = "Log out", navController = navController)
        }
    ){innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = "Are you sure you want to log out?",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
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
                },
                modifier = Modifier.padding(start = 12.dp)
            ) {
                Text(text = "Log out")
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun PreviewLogoutScreen(){
    LogoutScreen(navController = NavHostController(LocalContext.current))
}
