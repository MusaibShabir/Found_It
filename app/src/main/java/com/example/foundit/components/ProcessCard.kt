package com.example.foundit.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foundit.ui.theme.MainGreen
import com.example.foundit.ui.theme.MainRed

@Composable
fun ProcessCard(
    modifier: Modifier,
    cardColorCode: Int
) {
    // Defining Card Color
    val cardColor = when (cardColorCode) {
        0 -> MainRed
        1 -> MainGreen
        else -> Color.Gray
    }
    Card(
        onClick = { /*TODO*/ },
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {

    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewProcessCard() {
    ProcessCard(modifier = Modifier, cardColorCode = 0)
}