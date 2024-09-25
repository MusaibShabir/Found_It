package com.example.foundit.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.foundit.presentation.data.navigation.NavRoutes
import com.example.foundit.presentation.navigation.NavigationBar
import com.example.foundit.presentation.screens.documentation.PrivacyPolicyScreen
import com.example.foundit.presentation.screens.documentation.TermsOfServiceScreen
import com.example.foundit.presentation.screens.home.HomeScreen
import com.example.foundit.presentation.screens.input.UserItemInputScreen
import com.example.foundit.presentation.screens.notification.NotificationBaseViewModel
import com.example.foundit.presentation.screens.notification.NotificationScreen
import com.example.foundit.presentation.screens.profile.ProfileScreen
import com.example.foundit.presentation.screens.profile.ProfileViewModel
import com.example.foundit.presentation.screens.profile.components.EditProfileScreen
import com.example.foundit.presentation.screens.progress.ProcessScreen
import com.example.foundit.presentation.screens.progress.components.MatchedCardFullScreen
import com.example.foundit.presentation.screens.progress.components.ProgressCardFullScreen
import com.example.foundit.presentation.screens.registration.ForgotPasswordScreen
import com.example.foundit.presentation.screens.registration.GetStartedScreen
import com.example.foundit.presentation.screens.registration.components.google.ContinueWithGoogleViewModel
import com.example.foundit.presentation.screens.registration.login.LoginScreen
import com.example.foundit.presentation.screens.registration.login.LoginViewModel
import com.example.foundit.presentation.screens.registration.signup.SignUpScreen
import com.example.foundit.presentation.screens.registration.signup.SignUpViewModel
import com.example.foundit.presentation.screens.settings.SettingsScreen
import com.example.foundit.presentation.screens.settings.components.clickable.about.AboutScreen
import com.example.foundit.presentation.screens.settings.components.clickable.about.AcknowledgementScreen
import com.example.foundit.presentation.screens.settings.components.clickable.about.DeveloperInfoScreen
import com.example.foundit.presentation.screens.settings.components.clickable.account_center.AccountCenterScreen
import com.example.foundit.presentation.screens.settings.components.clickable.account_center.ChangeEmailScreen
import com.example.foundit.presentation.screens.settings.components.clickable.account_center.ChangePasswordScreen
import com.example.foundit.presentation.screens.settings.components.clickable.account_center.ChangePhoneNumberScreen
import com.example.foundit.presentation.screens.settings.components.clickable.account_center.DeleteAccountScreen
import com.example.foundit.presentation.screens.settings.components.clickable.appearance.AppearanceScreen
import com.example.foundit.presentation.screens.settings.components.clickable.feedback.FeedbackScreen
import com.example.foundit.presentation.screens.settings.components.clickable.help_and_Support.ContactSupportScreen
import com.example.foundit.presentation.screens.settings.components.clickable.help_and_Support.HelpAndSupportScreen
import com.example.foundit.presentation.screens.settings.components.clickable.help_and_Support.ReportBugScreen
import com.example.foundit.presentation.screens.settings.components.clickable.security.SecurityScreen
import com.example.foundit.presentation.splash.SplashScreen

/*

It contains some of the Animations for the bottom navigation bar

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

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MainScreen(modifier: Modifier) {
    val navController = rememberNavController()
    val currentRoute = currentRoute(navController)

    // ViewModels Instances
    val loginViewModel: LoginViewModel = hiltViewModel()
    val signUpViewModel: SignUpViewModel = hiltViewModel()
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val continueWithGoogleViewModel: ContinueWithGoogleViewModel = hiltViewModel()
    val notificationBaseViewModel: NotificationBaseViewModel = hiltViewModel()

    Scaffold(
        bottomBar = {

            // Conditionally include the NavigationBar based on the current route
            if (shouldShowBottomBar(currentRoute)) {
                NavigationBar(modifier = modifier, navController = navController)
            }
        },

    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavRoutes.SPLASH,
            modifier = modifier.padding(innerPadding)
        ) {

            composable(NavRoutes.SPLASH) {
                SplashScreen(navController = navController, forwardNavigation = NavRoutes.GET_STARTED)
            }

            // Screens WITH Navigation Bar
            composable(NavRoutes.HOME) {
                HomeScreen(
                    modifier = modifier,
                    viewModel = profileViewModel,
                    navController = navController,
                    lostButtonClick = "${NavRoutes.USER_ITEM_INPUT_SCREEN}/0",
                    foundButtonClick = "${NavRoutes.USER_ITEM_INPUT_SCREEN}/1",
                )
            }


            composable(
                route = NavRoutes.USER_ITEM_INPUT_SCREEN + "/{cardType}",
                arguments = listOf(navArgument("cardType") { type = NavType.IntType })
            ) { //backStackEntry ->
                //val cardType = backStackEntry.arguments?.getInt("cardType")
                UserItemInputScreen(
                    modifier = modifier,
                    navController = navController
                )
            }

            composable(NavRoutes.PROGRESS) {
                ProcessScreen(modifier, navController)
            }

            composable(
                NavRoutes.PROGRESS_CARD_FULL_SCREEN + "/{cardId}",
                arguments = listOf(navArgument("cardId") { type = NavType.StringType })
            ) {
                val cardId = it.arguments?.getString("cardId")
                ProgressCardFullScreen(modifier = modifier, cardId = cardId.toString(), navController = navController)
            }

            composable(
                NavRoutes.MATCHED_CARD_FULL_SCREEN + "/{cardId}",
                arguments = listOf(navArgument("cardId") { type = NavType.StringType })
            ) {
                val cardId = it.arguments?.getString("cardId")
                MatchedCardFullScreen(modifier = modifier, cardId = cardId.toString(), navController = navController)
            }

            composable(NavRoutes.NOTIFICATIONS) {
                NotificationScreen(
                    modifier = modifier,
                    navController = navController,
                    viewModel = notificationBaseViewModel
                )
            }

            composable(NavRoutes.PROFILE) {
                ProfileScreen(modifier, navController, profileViewModel)
            }

            composable(NavRoutes.ACCOUNT_CENTER) {
                AccountCenterScreen(modifier, navController)
            }

            composable(NavRoutes.APPEARANCE) {
                AppearanceScreen(modifier = modifier, navController = navController, onThemeChange = { })
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
                DeleteAccountScreen(navController = navController, modifier = modifier)
            }

            composable(NavRoutes.REPORT_A_BUG) {
                ReportBugScreen(modifier, navController)
            }

            composable(NavRoutes.CONTACT_SUPPORT) {
                ContactSupportScreen(modifier, navController)
            }

            composable(NavRoutes.PRIVACY_POLICY) {
                PrivacyPolicyScreen(modifier = modifier, navController = navController)
            }

            composable(NavRoutes.TERMS_OF_SERVICE) {
                TermsOfServiceScreen(
                    modifier = modifier,
                    navController = navController
                )
            }

            composable(NavRoutes.ACKNOWLEDGMENTS) {
                AcknowledgementScreen(modifier = modifier, navController = navController)
            }

            composable(NavRoutes.DEVELOPER_INFO) {
                DeveloperInfoScreen(modifier = modifier, navController = navController)
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
                LoginScreen(modifier = modifier, navController = navController, loginViewModel = loginViewModel, continueWithGoogleViewModel = continueWithGoogleViewModel)
            }

            composable(NavRoutes.SIGN_UP) {
                SignUpScreen(modifier = modifier, navController = navController, signUpViewModel = signUpViewModel, continueWithGoogleViewModel = continueWithGoogleViewModel)
            }

            composable(NavRoutes.GET_STARTED) {
                GetStartedScreen(modifier = modifier, navController = navController, forwardNavigation = NavRoutes.SIGN_UP)

            }
            composable(NavRoutes.FORGOT_PASSWORD) {
                ForgotPasswordScreen(modifier = modifier, navController = navController)
            }

            composable(NavRoutes.SETTINGS) {
                SettingsScreen(modifier = modifier, navController = navController)
            }
        }
    }
}

// Helper function to determine if the navigation bar should be shown
@Composable
fun shouldShowBottomBar(currentRoute: String?): Boolean {
    return when (currentRoute) {
        NavRoutes.HOME,
        NavRoutes.PROGRESS,
        NavRoutes.NOTIFICATIONS,
        NavRoutes.PROFILE, -> true
        else -> false
    }
}






