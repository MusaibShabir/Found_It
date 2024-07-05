package com.example.foundit.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foundit.R
import com.example.foundit.components.NavigationBar

@Composable
fun MainScreen(modifier: Modifier) {
    var currentScreenIndex by rememberSaveable { mutableIntStateOf(0) }
    val navController = rememberNavController()
    Scaffold(
        modifier = modifier,
        bottomBar = { NavigationBar(
            modifier = modifier,
            onItemSelected = { index -> currentScreenIndex = index },
            navController = navController
        ) }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen(modifier = modifier) }
            composable("profile") {
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