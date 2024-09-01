package com.example.foundit.presentation.screens.settings.components.clickable.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foundit.R
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
            DeveloperInfoCard(
                profilePicture = painterResource(id = R.drawable.ic_launcher_background),
                firstName = "Sofi",
                lastName = "Burhon",
                title = "UI-UX Developer"
            )
        }
    }
}

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun PreviewDeveloperInfoScreen() {
    DeveloperInfoScreen(navController = NavHostController(LocalContext.current))
}



