package com.example.foundit.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foundit.presentation.data.navigation.NavRoutes
import com.example.foundit.presentation.navigation.NavigationBar
import com.example.foundit.presentation.screens.documentation.PrivacyPolicyScreen
import com.example.foundit.presentation.screens.home.HomeScreen
import com.example.foundit.presentation.screens.notification.NotificationScreen
import com.example.foundit.presentation.screens.profile.ProfileScreen
import com.example.foundit.presentation.screens.profile.ProfileViewModel
import com.example.foundit.presentation.screens.profile.components.EditProfileScreen
import com.example.foundit.presentation.screens.progress.ProcessScreen
import com.example.foundit.presentation.screens.registration.GetStartedScreen
import com.example.foundit.presentation.screens.registration.login.LoginScreen
import com.example.foundit.presentation.screens.registration.login.LoginViewModel
import com.example.foundit.presentation.screens.registration.signup.SignUpScreen
import com.example.foundit.presentation.screens.registration.signup.SignUpViewModel
import com.example.foundit.presentation.screens.settings.SettingsScreen
import com.example.foundit.presentation.screens.settings.components.clickable.AboutScreen
import com.example.foundit.presentation.screens.settings.components.clickable.AccountCenterScreen
import com.example.foundit.presentation.screens.settings.components.clickable.AcknowledgementScreen
import com.example.foundit.presentation.screens.settings.components.clickable.AppearanceScreen
import com.example.foundit.presentation.screens.settings.components.clickable.ChangeEmailScreen
import com.example.foundit.presentation.screens.settings.components.clickable.ChangePasswordScreen
import com.example.foundit.presentation.screens.settings.components.clickable.ChangePhoneNumberScreen
import com.example.foundit.presentation.screens.settings.components.clickable.ContactSupportScreen
import com.example.foundit.presentation.screens.settings.components.clickable.DeleteAccountScreen
import com.example.foundit.presentation.screens.settings.components.clickable.DeveloperInfoScreen
import com.example.foundit.presentation.screens.settings.components.clickable.FeedbackScreen
import com.example.foundit.presentation.screens.settings.components.clickable.FollowUsScreen
import com.example.foundit.presentation.screens.settings.components.clickable.HelpAndSupportScreen
import com.example.foundit.presentation.screens.settings.components.clickable.LanguageScreen
import com.example.foundit.presentation.screens.settings.components.clickable.LogoutScreen
import com.example.foundit.presentation.screens.settings.components.clickable.ReportBugScreen
import com.example.foundit.presentation.screens.settings.components.clickable.SecurityScreen
import com.example.foundit.presentation.screens.settings.components.clickable.VersionScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(modifier: Modifier) {
    val navController = rememberNavController()

    val loginViewModel: LoginViewModel = hiltViewModel()
    val signUpViewModel: SignUpViewModel = hiltViewModel()
    val profileViewModel: ProfileViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.LOGIN,
        modifier = modifier
    ) {
        // Screens WITH Navigation Bar
        composable(NavRoutes.HOME) {
            Scaffold(bottomBar = { NavigationBar(modifier, navController) }){
                HomeScreen(modifier, profileViewModel)
            }
        }
        composable(NavRoutes.PROGRESS) {
            Scaffold(bottomBar = { NavigationBar(modifier, navController) }) {
                ProcessScreen(modifier, navController)
            }
        }
        composable(NavRoutes.NOTIFICATIONS) {
            Scaffold(bottomBar = { NavigationBar(modifier, navController) }) {
            NotificationScreen(modifier, navController)
        }
        }
        composable(NavRoutes.PROFILE) {
            Scaffold(bottomBar = { NavigationBar(modifier, navController) }) {
                ProfileScreen(modifier, navController, profileViewModel)
            }
        }
        composable(NavRoutes.SETTINGS) {
            Scaffold(bottomBar = { NavigationBar(modifier, navController) }) {
                SettingsScreen(modifier, navController)
            }
        }
        composable(NavRoutes.ACCOUNT_CENTER) {
            Scaffold(bottomBar = { NavigationBar(modifier, navController) }) {
                AccountCenterScreen(modifier, navController)
            }
        }
        composable(NavRoutes.LANGUAGE) {
            Scaffold(bottomBar = { NavigationBar(modifier, navController) }) {
                LanguageScreen(modifier, navController)
            }
        }
        composable(NavRoutes.APPEARANCE) {
            Scaffold(bottomBar = { NavigationBar(modifier, navController) }) {
                AppearanceScreen(modifier = modifier, navController = navController, onThemeChange = { /*TODO*/ })
            }
        }
        composable(NavRoutes.SECURITY) {
            Scaffold(bottomBar = { NavigationBar(modifier, navController) }) {
                SecurityScreen(modifier, navController)
            }
        }
        composable(NavRoutes.HELP_AND_SUPPORT) {
            Scaffold(bottomBar = { NavigationBar(modifier, navController) }) {
                HelpAndSupportScreen(modifier, navController)
            }
        }
        composable(NavRoutes.FEEDBACK) {
            Scaffold(bottomBar = { NavigationBar(modifier, navController) }) {
                FeedbackScreen(modifier, navController)
            }
        }
        composable(NavRoutes.ABOUT) {
            Scaffold(bottomBar = { NavigationBar(modifier, navController) }) {
                AboutScreen(modifier, navController)
            }
        }
        composable(NavRoutes.EDIT_PROFILE) {
            Scaffold(bottomBar = { NavigationBar(modifier, navController) }) {
                EditProfileScreen(modifier, navController, profileViewModel)
            }

        }
        composable(NavRoutes.DELETE_ACCOUNT) {
            Scaffold(bottomBar = { NavigationBar(modifier, navController) }) {
                DeleteAccountScreen(modifier = modifier, navController = navController, onDeleteAccount = { /*TODO*/ })
            }

        }
        composable(NavRoutes.LOG_OUT) {
            Scaffold(bottomBar = { NavigationBar(modifier, navController) }) {
                LogoutScreen(modifier = modifier, navController = navController, onLogout = { /*TODO*/ })
            }

        }
        composable(NavRoutes.REPORT_A_BUG) {
            Scaffold(bottomBar = { NavigationBar(modifier, navController) }) {
                ReportBugScreen(modifier, navController)
            }
        }
        composable(NavRoutes.CONTACT_SUPPORT) {
            Scaffold(bottomBar = { NavigationBar(modifier, navController) }) {
                ContactSupportScreen(modifier, navController)
            }
        }
        composable(NavRoutes.VERSION) {
            Scaffold(bottomBar = { NavigationBar(modifier, navController) }) {
                VersionScreen(modifier = modifier, navController = navController)
            }
        }
        composable(NavRoutes.PRIVACY_POLICY) {
            Scaffold(bottomBar = { NavigationBar(modifier, navController) }) {
                PrivacyPolicyScreen(modifier = modifier, navController = navController)
            }
        }
        composable(NavRoutes.ACKNOWLEDGMENTS) {
            Scaffold(bottomBar = { NavigationBar(modifier, navController) }) {
                AcknowledgementScreen(modifier = modifier, navController = navController)
            }
        }
        composable(NavRoutes.DEVELOPER_INFO) {
            Scaffold(bottomBar = { NavigationBar(modifier, navController) }) {
                DeveloperInfoScreen(modifier = modifier, navController = navController)
            }
        }
        composable(NavRoutes.FOLLOW_US) {
            Scaffold(bottomBar = { NavigationBar(modifier, navController) }) {
                FollowUsScreen(modifier = modifier, navController = navController)
            }
        }
        composable(NavRoutes.CHANGE_PASSWORD) {
            Scaffold(bottomBar = { NavigationBar(modifier, navController) }) {
                ChangePasswordScreen(modifier = modifier, navController = navController)
            }
        }
        composable(NavRoutes.CHANGE_EMAIL) {
            Scaffold(bottomBar = { NavigationBar(modifier, navController) }) {
                ChangeEmailScreen(modifier = modifier, navController = navController)
            }
        }
        composable(NavRoutes.CHANGE_PHONE_NUMBER) {
            Scaffold(bottomBar = { NavigationBar(modifier, navController) }) {
                ChangePhoneNumberScreen(modifier = modifier, navController = navController)
            }
        }


        // Screens WITHOUT Navigation Bar
        composable(NavRoutes.LOGIN) {
            LoginScreen(modifier = modifier, navController = navController, viewModel = loginViewModel)
        }
        composable(NavRoutes.SIGN_UP) {
            SignUpScreen(modifier = modifier, navController = navController, viewModel = signUpViewModel)
        }
        composable(NavRoutes.GET_STARTED) {
            GetStartedScreen(modifier = modifier, navController = navController, forwardNavigation = NavRoutes.SIGN_UP)

        }

    }


}



