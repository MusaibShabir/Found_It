package com.example.foundit.presentation.screens.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController


@Composable
fun SettingsOptionCard(
    modifier: Modifier,
    settingsOptionName: String,
    forwardNavigation: String? = null,
    navController: NavController
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                if (forwardNavigation != null) {
                    navController.navigate(forwardNavigation)
                }
            },
    ) {
        Text(
            text = settingsOptionName,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
        )
        IconButton(onClick = {
            if (forwardNavigation != null) {
                navController.navigate(forwardNavigation)
            }
        }) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos,
                contentDescription = null
            )
        }


    }

}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSettingsOptionCard(){
    SettingsOptionCard(
        modifier = Modifier,
        settingsOptionName = "Appearance",
        forwardNavigation = "Settings",
        navController = NavHostController(LocalContext.current)
    )
}