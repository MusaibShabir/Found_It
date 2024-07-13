package com.example.foundit.data

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun NavigationIcon(painter: Painter)  {
    Icon(
        painter = painter,
        contentDescription = "",
        tint = Color.Black
    )
}