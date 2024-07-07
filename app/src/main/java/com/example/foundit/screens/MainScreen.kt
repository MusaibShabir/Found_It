package com.example.foundit.screens

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
import com.example.foundit.components.NavigationBar
import com.example.foundit.data.NavRoutes

@Composable
fun MainScreen(modifier: Modifier) {
    val navController = rememberNavController()
    Scaffold(
        modifier = modifier,
        bottomBar = { NavigationBar(modifier = modifier, navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavRoutes.HOME,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavRoutes.HOME) { HomeScreen(modifier = modifier) }
            composable(NavRoutes.PROCESS) { ProcessScreen(modifier = modifier) }
            composable(NavRoutes.NOTIFICATIONS) { NotificationScreen(modifier = modifier) }
            composable(NavRoutes.PROFILE) {
                ProfileScreen(
                    modifier = Modifier,
                    profileName = "Musaib Shabir",
                    profilePicture = painterResource(id = R.drawable.ic_launcher_background),
                    profileCountryFlag = painterResource(id = R.drawable.flag_in),
                    profileCountryCode = "IND",
                    profileId = 234567890,
                    badges = badgeImages,
                    foundScore = 10,
                    reportedScore = 5,
                    memberSince = "10 - June - 2024"
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewMainScreen() {
    MainScreen(modifier = Modifier)
}