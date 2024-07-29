package com.example.foundit.presentation.screens.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.foundit.R
import com.example.foundit.presentation.data.navigation.NavRoutes
import com.example.foundit.presentation.screens.profile.components.BadgeCard
import com.example.foundit.presentation.screens.profile.components.MemberSinceCardContent
import com.example.foundit.presentation.screens.profile.components.ProfileHeadingCard
import com.example.foundit.presentation.screens.profile.components.ProfileTopAppBar
import com.example.foundit.presentation.screens.profile.components.ScoreCard

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreenContent(
    modifier: Modifier = Modifier,
    profileFirstName: String,
    profileLastName: String,
    profilePicture: Painter,
    profileCountryCode: Int,
    profileId: Long,
    badgesCodes: List<Int>,
    foundScore: Int?,
    reportedScore: Int?,
    memberSince: String?,
    onEditProfileClick: () -> Unit,
    navController: NavController,
) {
    Scaffold(
        topBar = { ProfileTopAppBar(title = "Profile", navController = navController) }
    ) { innerPadding ->
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
                firstName = profileFirstName,
                lastName = profileLastName,
                profileId = profileId,
                profilePicture = profilePicture,
                profileCountryCode = profileCountryCode,
                onEditProfileClick = onEditProfileClick,

            )

            BadgeCard(
                userBadgeCodes = badgesCodes
            )


            ScoreCard(
                modifier = modifier,
                foundScore = foundScore,
                reportedScore = reportedScore
            )

            MemberSinceCardContent(
                modifier = modifier,
                memberSince = memberSince
            )
        }
    }
}


// ViewModel Composable
@Composable
fun ProfileScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: ProfileViewModel
) {

    val profileData by viewModel.profileData.collectAsState()

    //Profile Heading Card
    val profileFirstName by remember { mutableStateOf(profileData?.firstName ?: "") }
    val profileLastName by remember { mutableStateOf(profileData?.lastName ?: "") }
    val profileCountryCode by remember { mutableIntStateOf(profileData?.countryCode ?: 0) }
    val profilePicture = painterResource(id = R.drawable.ic_launcher_background)
    val profileId by remember { mutableLongStateOf(profileData?.id ?: 0) }

    //Badges Card
    val badgesCodes = listOf(1,2,3,4,5,6,7)

    //Score Card
    val foundScore by remember { mutableIntStateOf(profileData?.totalFound ?: 0) }
    val reportedScore by remember { mutableIntStateOf(profileData?.totalReported ?: 0) }

    //Member Since Card
    val memberSince by remember { mutableStateOf(profileData?.memberSince ?: "unknown") }

    ProfileScreenContent(
        modifier = modifier,
        profileFirstName = profileFirstName,
        profileLastName = profileLastName,
        profileId = profileId,
        profilePicture = profilePicture,
        profileCountryCode = profileCountryCode,
        badgesCodes = badgesCodes,
        foundScore = foundScore,
        reportedScore = reportedScore,
        memberSince = memberSince,
        onEditProfileClick = { navController.navigate(NavRoutes.EDIT_PROFILE)},
        navController = navController
    )


}


@Composable
@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_6_pro")
fun PreviewProfileScreen() {
    ProfileScreenContent(
        modifier = Modifier,
        profilePicture = painterResource(id = R.drawable.ic_launcher_background),
        profileFirstName = "Musaib",
        profileLastName = "Shabir",
        profileCountryCode = 91,
        badgesCodes = listOf(1,2,3,4,5,6,7),
        profileId = 234567890,
        foundScore = 10,
        reportedScore = 5,
        memberSince = "10 - June - 2024",
        onEditProfileClick = { },
        navController = NavController(LocalContext.current)

    )
}

