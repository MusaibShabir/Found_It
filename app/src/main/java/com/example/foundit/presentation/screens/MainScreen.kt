package com.example.foundit.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foundit.R
import com.example.foundit.presentation.data.navigation.NavRoutes
import com.example.foundit.presentation.navigation.NavigationBar
import com.example.foundit.presentation.screens.documentation.PrivacyPolicyScreen
import com.example.foundit.presentation.screens.home.HomeScreen
import com.example.foundit.presentation.screens.notification.NotificationScreen
import com.example.foundit.presentation.screens.profile.ProfileScreen
import com.example.foundit.presentation.screens.profile.ProfileViewModel
import com.example.foundit.presentation.screens.profile.components.EditProfileScreen
import com.example.foundit.presentation.screens.profile.components.userBadgeCodes
import com.example.foundit.presentation.screens.progress.ProcessScreen
import com.example.foundit.presentation.screens.registration.GetStartedScreen
import com.example.foundit.presentation.screens.registration.LoginScreen
import com.example.foundit.presentation.screens.registration.SignUpScreen
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

@Composable
fun MainScreen(modifier: Modifier) {
    val navController = rememberNavController()
    val viewmodel: ProfileViewModel = viewModel()
    Scaffold(
        bottomBar = { NavigationBar(modifier = modifier, navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavRoutes.GET_STARTED,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(NavRoutes.HOME) { HomeScreen(modifier = modifier) }
            composable(NavRoutes.PROGRESS) { ProcessScreen(modifier = modifier, navController = navController) }
            composable(NavRoutes.NOTIFICATIONS) { NotificationScreen(modifier = modifier, navController = navController) }
            composable(NavRoutes.PROFILE) {
                ProfileScreen(
                    modifier = modifier,
                    profilePicture = painterResource(id = R.drawable.ic_launcher_background),
                    profileCountryFlag = painterResource(id = R.drawable.flag_in),
                    profileCountryCode = "IND",
                    //profileId = 234567890,
                    badgesCodes = userBadgeCodes,
                    foundScore = 10,
                    reportedScore = 5,
                    memberSince = "10 - June - 2024",
                    navController = navController,
                    viewModel = viewmodel
                )
            }
            composable(NavRoutes.SETTINGS) { SettingsScreen(modifier = modifier, navController = navController)}

            composable(NavRoutes.ACCOUNT_CENTER) { AccountCenterScreen(modifier = modifier, navController = navController) }
            composable(NavRoutes.LANGUAGE) { LanguageScreen(modifier = modifier, navController = navController)}
            composable(NavRoutes.APPEARANCE) { AppearanceScreen(modifier = modifier, navController = navController, onThemeChange = { /*TODO*/ }) }
            composable(NavRoutes.SECURITY) { SecurityScreen(modifier = modifier, navController = navController) }
            composable(NavRoutes.HELP_AND_SUPPORT) { HelpAndSupportScreen(modifier = modifier, navController = navController) }
            composable(NavRoutes.FEEDBACK) { FeedbackScreen(modifier = modifier, navController = navController) }
            composable(NavRoutes.ABOUT) { AboutScreen(modifier = modifier, navController = navController) }
            composable(NavRoutes.EDIT_PROFILE) { EditProfileScreen(modifier = modifier, navController = navController) }
            composable(NavRoutes.DELETE_ACCOUNT) { DeleteAccountScreen(modifier = modifier, navController = navController, onDeleteAccount = { /*TODO*/ }) }
            composable(NavRoutes.LOG_OUT) { LogoutScreen(modifier = modifier, navController = navController, onLogout = { /*TODO*/ })}
            composable(NavRoutes.REPORT_A_BUG) { ReportBugScreen(modifier = modifier, navController = navController)}
            composable(NavRoutes.CONTACT_SUPPORT) { ContactSupportScreen(modifier = modifier, navController = navController) }
            composable(NavRoutes.VERSION) { VersionScreen(modifier = modifier, navController = navController) }
            composable(NavRoutes.PRIVACY_POLICY) { PrivacyPolicyScreen(modifier = modifier, navController = navController) }
            composable(NavRoutes.ACKNOWLEDGMENTS) { AcknowledgementScreen(modifier = modifier, navController = navController) }
            composable(NavRoutes.DEVELOPER_INFO) { DeveloperInfoScreen(modifier = modifier, navController = navController) }
            composable(NavRoutes.FOLLOW_US) { FollowUsScreen(modifier = modifier, navController = navController) }
            composable(NavRoutes.CHANGE_PASSWORD) { ChangePasswordScreen(modifier = modifier, navController = navController) }
            composable(NavRoutes.CHANGE_EMAIL) { ChangeEmailScreen(modifier = modifier, navController = navController) }
            composable(NavRoutes.CHANGE_PHONE_NUMBER) { ChangePhoneNumberScreen(modifier = modifier, navController = navController) }

            composable(NavRoutes.GET_STARTED) { GetStartedScreen(modifier = modifier, navController = navController, forwardNavigation = NavRoutes.LOGIN)}
            composable(NavRoutes.LOGIN) { LoginScreen(modifier = modifier)}
            composable(NavRoutes.SIGN_UP) { SignUpScreen(modifier = modifier)}
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_6_pro")
@Composable
fun PreviewMainScreen() {
    MainScreen(modifier = Modifier)
}