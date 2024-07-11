package com.example.foundit.presentation.screens.notification_screen.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.foundit.data.NavRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsTopAppBar(
    title: String,
    navController: NavController,
) {
    androidx.compose.material3.TopAppBar(
        title = {
            Text(
                text = title,
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(NavRoutes.HOME) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = null
                )
            }
        },
    )
}