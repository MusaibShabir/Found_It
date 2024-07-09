package com.example.foundit.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.foundit.data.NavRoutes

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TheTopAppBar(
    title: String,
    navController: NavController
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
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = null
                )
            }
        }
    )
}