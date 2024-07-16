package com.example.foundit.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foundit.R
import com.example.foundit.data.NavRoutes
import com.example.foundit.presentation.navigation.NavigationBar
import com.example.foundit.presentation.screens.home_screen.HomeScreen
import com.example.foundit.presentation.screens.notification_screen.NotificationScreen
import com.example.foundit.presentation.screens.progress_screen.ProcessScreen
import com.example.foundit.presentation.screens.profile_screen.ProfileScreen
import com.example.foundit.presentation.screens.profile_screen.components.userBadgeCodes

@Composable
fun MainScreen(modifier: Modifier) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { NavigationBar(modifier = modifier, navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavRoutes.HOME,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(NavRoutes.HOME) { HomeScreen(modifier = modifier) }
            composable(NavRoutes.PROGRESS) { ProcessScreen(modifier = modifier, navController = navController) }
            composable(NavRoutes.NOTIFICATIONS) { NotificationScreen(modifier = modifier, navController = navController) }
            composable(NavRoutes.PROFILE) {
                ProfileScreen(
                    modifier = modifier,
                    profileName = "Musaib Shabir",
                    profilePicture = painterResource(id = R.drawable.ic_launcher_background),
                    profileCountryFlag = painterResource(id = R.drawable.flag_in),
                    profileCountryCode = "IND",
                    profileId = 234567890,
                    badgesCodes = userBadgeCodes,
                    foundScore = 10,
                    reportedScore = 5,
                    memberSince = "10 - June - 2024",
                    navController = navController
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_6_pro")
fun PreviewMainScreen() {
    MainScreen(modifier = Modifier)
}