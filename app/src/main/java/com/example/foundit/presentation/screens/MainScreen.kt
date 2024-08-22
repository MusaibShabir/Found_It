package com.example.foundit.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.foundit.presentation.data.navigation.NavRoutes
import com.example.foundit.presentation.splash.SplashScreen
import com.example.foundit.presentation.navigation.NavigationBar
import com.example.foundit.presentation.screens.documentation.PrivacyPolicyScreen
import com.example.foundit.presentation.screens.home.HomeScreen
import com.example.foundit.presentation.screens.notification.NotificationScreen
import com.example.foundit.presentation.screens.profile.ProfileScreen
import com.example.foundit.presentation.screens.profile.ProfileViewModel
import com.example.foundit.presentation.screens.profile.components.EditProfileScreen
import com.example.foundit.presentation.screens.progress.ProcessScreen
import com.example.foundit.presentation.screens.registration.ForgotPasswordScreen
import com.example.foundit.presentation.screens.registration.GetStartedScreen
import com.example.foundit.presentation.screens.registration.components.google.ContinueWithGoogle
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

/*
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedContentLambdaTargetStateParameter")
@Composable
fun ScreenWithNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = { NavigationBar(modifier = modifier, navController = navController) }
    ) {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        AnimatedContent(
            targetState = currentRoute,
            transitionSpec = {

                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(500000, easing = FastOutSlowInEasing)
                ) togetherWith
                        slideOutHorizontally(
                            targetOffsetX = { fullWidth -> -fullWidth },
                            animationSpec = tween(5200000, easing = FastOutSlowInEasing)
                        ) using(SizeTransform(clip = false))
            },
            label = ""
        ) {
            content()
        }
    }
}

 */

// Helper function to get the current route
@Composable
fun currentRoute(navController: NavHostController): String {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route ?: ""
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(modifier: Modifier) {
    val navController = rememberNavController()
    val currentRoute = currentRoute(navController)

    // ViewModels Instances
    val loginViewModel: LoginViewModel = hiltViewModel()
    val signUpViewModel: SignUpViewModel = hiltViewModel()
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val googleViewModel: ContinueWithGoogle = hiltViewModel()

    Scaffold(
        bottomBar = {

            // Conditionally include the NavigationBar based on the current route
            if (shouldShowBottomBar(currentRoute)) {
                NavigationBar(modifier = modifier, navController = navController)
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = NavRoutes.SPLASH,
            modifier = modifier
        ) {

            composable(NavRoutes.SPLASH) {
                SplashScreen(navController = navController, forwardNavigation = NavRoutes.GET_STARTED)
            }

            // Screens WITH Navigation Bar
            composable(NavRoutes.HOME) {
                HomeScreen(modifier, profileViewModel)
            }

            composable(NavRoutes.PROGRESS) {
                ProcessScreen(modifier, navController)
            }

            composable(NavRoutes.NOTIFICATIONS) {
                NotificationScreen(modifier, navController)
            }

            composable(NavRoutes.PROFILE) {
                ProfileScreen(modifier, navController, profileViewModel)
            }

            composable(NavRoutes.SETTINGS) {
                SettingsScreen(modifier, navController)
            }

            composable(NavRoutes.ACCOUNT_CENTER) {
                AccountCenterScreen(modifier, navController)
            }

            composable(NavRoutes.LANGUAGE) {
                LanguageScreen(modifier, navController)
            }

            composable(NavRoutes.APPEARANCE) {
                AppearanceScreen(modifier = modifier, navController = navController, onThemeChange = { /*TODO*/ })
            }

            composable(NavRoutes.SECURITY) {
                SecurityScreen(modifier, navController)
            }

            composable(NavRoutes.HELP_AND_SUPPORT) {
                HelpAndSupportScreen(modifier, navController)
            }

            composable(NavRoutes.FEEDBACK) {
                FeedbackScreen(modifier, navController)
            }

            composable(NavRoutes.ABOUT) {
                AboutScreen(modifier, navController)
            }

            composable(NavRoutes.EDIT_PROFILE) {
                EditProfileScreen(modifier, navController, profileViewModel)
            }


            composable(NavRoutes.DELETE_ACCOUNT) {
                DeleteAccountScreen(modifier = modifier, navController = navController, onDeleteAccount = { /*TODO*/ })
            }

            composable(NavRoutes.LOG_OUT) {
                LogoutScreen(navController = navController, modifier = modifier)
            }


            composable(NavRoutes.REPORT_A_BUG) {
                ReportBugScreen(modifier, navController)
            }

            composable(NavRoutes.CONTACT_SUPPORT) {
                ContactSupportScreen(modifier, navController)
            }

            composable(NavRoutes.VERSION) {
                VersionScreen(modifier = modifier, navController = navController)
            }

            composable(NavRoutes.PRIVACY_POLICY) {
                PrivacyPolicyScreen(modifier = modifier, navController = navController)
            }

            composable(NavRoutes.ACKNOWLEDGMENTS) {
                AcknowledgementScreen(modifier = modifier, navController = navController)
            }

            composable(NavRoutes.DEVELOPER_INFO) {
                DeveloperInfoScreen(modifier = modifier, navController = navController)
            }

            composable(NavRoutes.FOLLOW_US) {
                FollowUsScreen(modifier = modifier, navController = navController)
            }

            composable(NavRoutes.CHANGE_PASSWORD) {
                ChangePasswordScreen(modifier = modifier, navController = navController)
            }

            composable(NavRoutes.CHANGE_EMAIL) {
                ChangeEmailScreen(modifier = modifier, navController = navController)
            }

            composable(NavRoutes.CHANGE_PHONE_NUMBER) {
                ChangePhoneNumberScreen(modifier = modifier, navController = navController)
            }


            // Screens WITHOUT Navigation Bar
            composable(NavRoutes.LOGIN) {
                LoginScreen(modifier = modifier, navController = navController, loginViewModel = loginViewModel, googleViewModel = googleViewModel)
            }

            composable(NavRoutes.SIGN_UP) {
                SignUpScreen(modifier = modifier, navController = navController, signUpViewModel = signUpViewModel, googleViewModel = googleViewModel)
            }

            composable(NavRoutes.GET_STARTED) {
                GetStartedScreen(modifier = modifier, navController = navController, forwardNavigation = NavRoutes.SIGN_UP)

            }
            composable(NavRoutes.FORGOT_PASSWORD) {
                ForgotPasswordScreen(modifier = modifier, navController = navController)

            }
        }
    }
}

// Helper function to determine if the navigation bar should be shown
@Composable
fun shouldShowBottomBar(currentRoute: String?): Boolean {
    return when (currentRoute) {
        NavRoutes.GET_STARTED,
        NavRoutes.FORGOT_PASSWORD,
        NavRoutes.LOGIN,
        NavRoutes.SPLASH,
        NavRoutes.SIGN_UP -> false
        else -> true
    }
}





