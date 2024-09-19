package com.example.foundit.presentation.screens.registration.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OrDivider(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 1.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        HorizontalDivider(
            modifier = Modifier
                .weight(1f)
                .padding(start = 60.dp),
            thickness = 0.4.dp,
            color = Color.Black
        )
        Text(
            text = "OR",
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 8.dp),
        )
        HorizontalDivider(
            modifier = Modifier
                .weight(1f)
                .padding(end = 60.dp),
            thickness = 0.4.dp,
            color = Color.Black
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = false)
fun PreviewOrDivider() {
    OrDivider(modifier = Modifier)
}

