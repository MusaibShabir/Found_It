package com.example.foundit.presentation.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalPolice
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Token
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.ui.graphics.vector.ImageVector

data class BadgesCardDataClass(
    val badgeCode: Int,
    val badgeName: String,
    val badgeImageVector: ImageVector
)

val BadgesCardData = listOf(
    BadgesCardDataClass(
        badgeCode = 1,
        badgeName = "Badge 1",
        badgeImageVector = Icons.Filled.WorkspacePremium
    ),
    BadgesCardDataClass(
        badgeCode = 2,
        badgeName = "Badge 2",
        badgeImageVector = Icons.Filled.MilitaryTech
    ),
    BadgesCardDataClass(
        badgeCode = 3,
        badgeName = "Badge 3",
        badgeImageVector = Icons.Filled.Star
    ),
    BadgesCardDataClass(
        badgeCode = 4,
        badgeName = "Badge 4",
        badgeImageVector = Icons.Filled.Verified
    ),
    BadgesCardDataClass(
        badgeCode = 5,
        badgeName = "Badge 5",
        badgeImageVector = Icons.Filled.Token
    ),
    BadgesCardDataClass(
        badgeCode = 7,
        badgeName = "Badge 7",
        badgeImageVector = Icons.Filled.LocalPolice
    ),
    BadgesCardDataClass(
        badgeCode = 8,
        badgeName = "Badge 8",
        badgeImageVector = Icons.Filled.Favorite
    ),
    BadgesCardDataClass(
        badgeCode = 9,
        badgeName = "Badge 9",
        badgeImageVector = Icons.Filled.Shield
    ),
    BadgesCardDataClass(
        badgeCode = 10,
        badgeName = "Badge 10",
        badgeImageVector = Icons.Filled.ThumbUp
    ),
    BadgesCardDataClass(
        badgeCode = 11,
        badgeName = "Badge 11",
        badgeImageVector = Icons.Filled.School
    ),
    BadgesCardDataClass(
        badgeCode = 12,
        badgeName = "Badge 12",
        badgeImageVector = Icons.Filled.Lock
    ),
    BadgesCardDataClass(
        badgeCode = 13,
        badgeName = "Badge 13",
        badgeImageVector = Icons.Filled.Celebration
    ),
)
