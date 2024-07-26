package com.example.foundit.presentation.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.navigation.NavController
import com.example.foundit.presentation.screens.profile.components.BadgeCard
import com.example.foundit.presentation.screens.profile.components.MemberSinceCard
import com.example.foundit.presentation.screens.profile.components.ProfileHeadingCard
import com.example.foundit.presentation.screens.profile.components.ProfileTopAppBar
import com.example.foundit.presentation.screens.profile.components.ScoreCard

@Composable
fun ProfileScreen(
    modifier: Modifier,
    profilePicture: Painter,
    profileCountryCode: String,
    badgesCodes: List<Int>,
    navController: NavController,
    viewModel: ProfileViewModel
) {
    Scaffold(
        topBar = { ProfileTopAppBar(title = "Profile", navController = navController) }
    ) {innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState(), true),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            ProfileHeadingCard(
                modifier = modifier,
                profilePicture = profilePicture,
                profileCountryCode = profileCountryCode,
                viewModel = viewModel ,
                navController = navController
            )

            BadgeCard(userBadgeCodes = badgesCodes)

            ScoreCard(modifier = modifier, viewModel = viewModel)
            MemberSinceCard(modifier = modifier, viewModel = viewModel)
        }
    }
}

/*
@Composable
@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_6_pro")
fun PreviewProfileScreen() {
    ProfileScreen(
        modifier = Modifier,
        profilePicture = painterResource(id = R.drawable.ic_launcher_background),
        profileCountryFlag = painterResource(id = R.drawable.flag_in),
        profileCountryCode = "IND",
        profileId = 234567890,
        badgesCodes = userBadgeCodes,
        foundScore = 10,
        reportedScore = 5,
        memberSince = "10 - June - 2024",
        navController = NavController(LocalContext.current)
    )
}

 */