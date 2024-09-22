package com.example.foundit.presentation.screens.profile.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.foundit.presentation.data.navigation.NavRoutes

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ProfileTopAppBar(
    title: String,
    navController: NavController,
) {
    androidx.compose.material3.TopAppBar(
        title = {
            Text(
                text = title,
            )
        },
//        navigationIcon = {
//            IconButton(onClick = { navController.navigate(NavRoutes.HOME) }) {
//                Icon(
//                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
//                    contentDescription = null
//                )
//            }
//        },
        actions = {
            IconButton(onClick = {navController.navigate(NavRoutes.SETTINGS)}) {
                Icon(
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = null
                )
            }
        }
    )
}