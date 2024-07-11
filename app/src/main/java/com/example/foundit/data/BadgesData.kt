package com.example.foundit.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalPolice
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.Star
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
)
