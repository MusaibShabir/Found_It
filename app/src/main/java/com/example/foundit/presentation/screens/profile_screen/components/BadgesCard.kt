package com.example.foundit.presentation.screens.profile_screen.components

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foundit.R

@Composable
fun BadgeCard(badges: List<Int>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(8.dp),

    ) {
        LazyRow(
            modifier = Modifier
                .padding(10.dp, 15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(badges) { badge ->
                BadgeItem(badgeImageRes = badge)
            }
        }
    }
}

@Composable
fun BadgeItem(badgeImageRes: Int) {
    Icon(
        painter = painterResource(id = badgeImageRes),
        contentDescription = "Badge",
        modifier = Modifier.size(48.dp),
        tint = Color.Black
    )

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewBadgeCard() {
        // Preview with a sample list of drawable resource IDs
        val badgeImages = listOf(
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground
        )
        BadgeCard(badges = badgeImages)

}
