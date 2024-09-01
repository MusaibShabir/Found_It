package com.example.foundit.presentation.screens.settings.components.clickable.appearance

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foundit.presentation.common.TheTopAppBar
import com.example.foundit.presentation.data.themes

@Composable
fun AppearanceScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onThemeChange: (String) -> Unit
) {
    var currentTheme by remember { mutableStateOf("System Default") }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TheTopAppBar(title = "Appearance Settings", navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Choose your preferred theme:",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            themes.forEach { theme ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = theme == currentTheme,
                        onClick = {
                            currentTheme = theme
                            onThemeChange(theme)
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = theme, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAppearanceScreen() {
    val navController = NavHostController(LocalContext.current)
    AppearanceScreen(
        navController = navController,
        onThemeChange = {}
    )
}