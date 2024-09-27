package com.example.foundit.presentation.screens.profile

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.foundit.presentation.data.BadgesCardData
import com.example.foundit.presentation.data.BadgesCardDataClass
import com.example.foundit.presentation.data.navigation.NavRoutes
import com.example.foundit.presentation.screens.profile.components.BadgeCard
import com.example.foundit.presentation.screens.profile.components.MemberSinceCardContent
import com.example.foundit.presentation.screens.profile.components.ProfileHeadingCard
import com.example.foundit.presentation.screens.profile.components.ProfileTopAppBar
import com.example.foundit.presentation.screens.profile.components.ScoreCard
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreenContent(
    modifier: Modifier = Modifier,
    profileFirstName: String,
    profileLastName: String,
    profilePicture: Uri?,
    profileCountryCode: Int,
    profileId: Long,
    badgesToDisplay: List<BadgesCardDataClass>,
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
                modifier = modifier,
                badgesData = badgesToDisplay
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
    val memberSince = viewModel.memberSince
//    val userName = viewModel.userName
//    val profilePicture: Uri? = viewModel.profilePicture //?: painterResource(id = R.drawable.ic_launcher_background)
    val userFirstName by viewModel.userFirstNames.collectAsState()
    val userLastName by viewModel.userLastNames.collectAsState()
    val profilePicture by viewModel.profilePicture.collectAsState()

    // dividing user name into firstName and lastName
//    val splitUserName = userName.split(" ", limit = 2)
//    val userNameList = if (splitUserName.size == 2) listOf(splitUserName[0], splitUserName[1]) else listOf(splitUserName[0], "")



    //Profile Heading Card
//    val profileFirstName by remember { mutableStateOf(userFirstName)}
//    val profileLastName by remember { mutableStateOf(userLastName) }
//    val profileFirstName by remember { mutableStateOf(profileData?.firstName ?: "") }
//    val profileLastName by remember { mutableStateOf(profileData?.lastName ?: "") }
    val profileCountryCode by remember { mutableIntStateOf(91) }
//    val profilePicture = painterResource(id = R.drawable.ic_launcher_background)
    val profileId by remember { mutableLongStateOf(23984874) }

    //Score Card
    val foundScore by remember { mutableIntStateOf(4) }
    val reportedScore by remember { mutableIntStateOf(6) }

    //Member Since Card
    //val memberSinceDate by remember { mutableStateOf(memberSince ?: "unknown") }

    ProfileScreenContent(
        modifier = modifier,
        profileFirstName = userFirstName,
        profileLastName = userLastName,
        profileId = profileId,
        profilePicture = profilePicture,
        profileCountryCode = profileCountryCode,
        badgesToDisplay = BadgesCardData,
        foundScore = foundScore,
        reportedScore = reportedScore,
        memberSince = formatTimestampToDate(memberSince),
        onEditProfileClick = { navController.navigate(NavRoutes.EDIT_PROFILE)},
        navController = navController
    )

    BackHandler(
        enabled = true,
    ) {

    }

}

fun formatTimestampToDate(timestamp: Long?, format: String = "yyyy-MM-dd"): String {
    return if (timestamp != null) {
        // Convert the timestamp to Instant
        val instant = Instant.ofEpochMilli(timestamp)

        // Create a DateTimeFormatter with the desired format
        val formatter = DateTimeFormatter.ofPattern(format)
            .withZone(ZoneId.systemDefault()) // Use system default time zone

        // Format the Instant into a date string
        formatter.format(instant)
    } else {
        "unknown" // Fallback value if timestamp is null
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_6_pro")
fun PreviewProfileScreen() {
    ProfileScreenContent(
        modifier = Modifier,
        profilePicture = null,
        profileFirstName = "Musaib",
        profileLastName = "Shabir",
        profileCountryCode = 91,
        badgesToDisplay = BadgesCardData,
        profileId = 234567890,
        foundScore = 10,
        reportedScore = 5,
        memberSince = "10 - June - 2024",
        onEditProfileClick = { },
        navController = NavController(LocalContext.current)

    )
}

