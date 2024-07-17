package com.example.foundit.presentation.screens.settings.components.clickable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foundit.presentation.common.TheTopAppBar

@Composable
fun DeveloperInfoScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TheTopAppBar(title = "Developer Information", navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
            .padding(horizontal = 16.dp)
        ) {
            Text(
                text = """
                    Developer Info

                    "Found it" is developed by:

                    Musaib Shabir
                      - Email: [Musaib’s email]
                      - LinkedIn: [Musaib’s LinkedIn]
                      
                    Qazi Mohammad Huzaif
                      - Email: [Huzaif’s email]
                      - LinkedIn: [Huzaif’s LinkedIn]
                      
                    Sofi Burhon Ahmad
                      - Email: [Burhon’s email]
                      - LinkedIn: [Burhon’s LinkedIn]

                    We are a team of Computer Engineering students from SSM College of Engineering, dedicated to creating innovative solutions that bridge the gap between lost and found items. Feel free to reach out to us for any queries or collaboration opportunities.
                """.trimIndent(),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun DeveloperInfoScreenPreview() {
    DeveloperInfoScreen(navController = NavHostController(LocalContext.current))
}