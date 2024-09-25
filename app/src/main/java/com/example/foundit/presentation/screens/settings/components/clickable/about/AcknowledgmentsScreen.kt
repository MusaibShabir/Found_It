package com.example.foundit.presentation.screens.settings.components.clickable.about

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
fun AcknowledgementScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TheTopAppBar(title = "Acknowledgments", navController = navController)
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
                    Acknowledgments

                    We would like to express our sincere gratitude to the following individuals and organizations for their support and contributions to the development of "Found it."

                    • SSM College of Engineering: For providing the educational resources and environment that made this project possible.
                    • Our Mentors:
                      - Mr. Irfan Rashid
                      - Mr. Khalid Makhdoomi
                      
                    • Google Firebase: For providing the backend services that power our real-time data storage and user authentication.
                    
                    • Open Source Communities: For the invaluable tools and libraries that facilitated our development process.

                    Thank you for your unwavering support and encouragement.
                """.trimIndent(),
                style = MaterialTheme.typography.bodySmall
            )

        }
    }
}

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun PreviewAcknowledgementScreen() {
    AcknowledgementScreen(navController = NavHostController(LocalContext.current))
}