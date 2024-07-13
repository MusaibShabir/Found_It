package com.example.foundit.presentation.navigation.components

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@Composable
fun NavigationIcon(iconResource: Int)  {
    Icon(
        painter = painterResource(id = iconResource),
        contentDescription = ""
    )
}