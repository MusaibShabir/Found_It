package com.example.foundit.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foundit.presentation.screens.home.HomeScreen
import com.example.foundit.presentation.screens.profile.ProfileViewModel
import com.example.foundit.presentation.screens.registration.login.LoginScreen
import com.example.foundit.presentation.screens.registration.login.LoginViewModel
import com.example.foundit.presentation.screens.registration.components.google.ContinueWithGoogleViewModel

@Composable
fun AppNavigation(
    loginViewModel: LoginViewModel, // Login ViewModel
    continueWithGoogleViewModel: ContinueWithGoogleViewModel // Google Login ViewModel
) {
    val navController = rememberNavController()

    // Define your navigation graph
    NavHost(navController = navController, startDestination = "login_screen") {

        // Login screen
        composable("login_screen") {
            LoginScreen(
                modifier = androidx.compose.ui.Modifier,
                loginViewModel = loginViewModel,
                continueWithGoogleViewModel = continueWithGoogleViewModel,
                navController = navController
            )
        }

        // Home screen
        composable("home_screen") {
            // Use ProfileViewModel for HomeScreen
            val profileViewModel: ProfileViewModel = hiltViewModel() // Inject the correct ViewModel

            HomeScreen(
                modifier = androidx.compose.ui.Modifier,
                viewModel = profileViewModel, // Use the correct ViewModel
                navController = navController,
                lostButtonClick = "lost_screen",
                foundButtonClick = "found_screen"
            )
        }

        // Add more routes/screens as needed (e.g., "lost_screen", "found_screen")
    }
}
