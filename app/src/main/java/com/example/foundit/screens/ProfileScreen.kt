package com.example.foundit.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.foundit.R
import com.example.foundit.components.BadgeCard
import com.example.foundit.components.MemberSinceCard
import com.example.foundit.components.ProfileHeadingCard
import com.example.foundit.components.ScoreCard

@Composable
fun ProfileScreen(
    modifier: Modifier,
    profileName: String,
    profilePicture: Painter,
    profileCountryFlag: Painter,
    profileCountryCode: String,
    profileId: Long,
    badges: List<Int>,
    foundScore: Int,
    reportedScore: Int,
    memberSince: String

) {
    Scaffold(
        modifier = modifier,
    ) {innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileHeadingCard(
                modifier = modifier,
                profileName = profileName,
                profilePicture = profilePicture,
                profileCountryFlag = profileCountryFlag,
                profileCountryCode = profileCountryCode,
                profileId = profileId
            )

            BadgeCard(badges = badges)

            ScoreCard(modifier = modifier , foundScore = foundScore, reportedScore = reportedScore)
            MemberSinceCard(modifier = modifier, date = memberSince)

        }

    }
}

// Sample Badges Painter Images (Later to be Changed)
val badgeImages = listOf(
    R.drawable.ic_launcher_foreground,
    R.drawable.ic_launcher_foreground,
    R.drawable.ic_launcher_foreground,
    R.drawable.ic_launcher_foreground
)



@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewProfileScreen() {
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