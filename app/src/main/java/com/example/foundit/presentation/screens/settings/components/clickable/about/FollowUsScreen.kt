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
fun FollowUsScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TheTopAppBar(title = "Follow us", navController = navController)
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
                    Follow Us

                    Stay connected and updated with the latest news and updates from "Found it" by following us on social media:

                    - Facebook: [Facebook Link]
                    - Twitter: [Twitter Link]
                    - Instagram: [Instagram Link]
                    - LinkedIn: [LinkedIn Link]

                    We look forward to engaging with our community and hearing your feedback.
                """.trimIndent(),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun PreviewFollowUsScreen() {
    FollowUsScreen(navController = NavHostController(LocalContext.current))
}