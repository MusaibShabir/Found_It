package com.example.foundit.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
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
            .padding(10.dp,15.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Gray // light green background
        )
    ) {
        LazyRow(
            modifier = Modifier
                .padding(10.dp,15.dp)
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        // Preview with a sample list of drawable resource IDs
        val badgeImages = listOf(
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground
        )
        BadgeCard(badges = badgeImages)
    }
}