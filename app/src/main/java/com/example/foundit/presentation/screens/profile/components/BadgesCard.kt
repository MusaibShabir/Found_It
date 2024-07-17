package com.example.foundit.presentation.screens.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foundit.presentation.data.BadgesCardData


val userBadgeCodes = listOf(1,2,3,4,5,6,7)

@Composable
fun BadgeCard(userBadgeCodes: List<Int>) {
    val badgesToDisplay = BadgesCardData.filter { it.badgeCode in userBadgeCodes }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(100.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(badgesToDisplay) { badgeData ->
                    BadgeItem(badgeImageVector = badgeData.badgeImageVector)
                }
            }
        }
    }
}

@Composable
fun BadgeItem(badgeImageVector: ImageVector) {
    Icon(
        imageVector = badgeImageVector,
        contentDescription = "Badge",
        modifier = Modifier.size(48.dp),
        tint= Color.Black
    )
}
@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewBadgeCard() {
        BadgeCard(userBadgeCodes = userBadgeCodes)

}
