package com.example.foundit.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProcessCard(
    modifier: Modifier,
    cardColorCode: Int
) {
    // Defining Card Color
    val cardColor = when (cardColorCode) {
        0 -> Color.Red  // Later to be Changed
        1 -> Color.Green // Later to be Changed
        else -> Color.Gray
    }
    Card(
        onClick = { /*TODO*/ },
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {

    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewProcessCard() {
    ProcessCard(modifier = Modifier, cardColorCode = 0)
}