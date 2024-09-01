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
        ) {

            //Musaib Shabir
            DeveloperInfoCard(
                profilePicture = painterResource(id = R.drawable.musaib_shabir_dp),
                firstName = "Musaib",
                lastName = "Shabir",
                title = "Team Lead & Senior Developer",
                githubLink = "https://github.com/musaibshabir",
                linkedInLink = "https://www.linkedin.com/in/musaib-shabir-dar/",
                twitterLink = "https://twitter.com/musaib_shabir",
                instagramLink = "https://www.instagram.com/musaib.shabir/"
            )

            //Qazi Huzaif
            DeveloperInfoCard(
                profilePicture = painterResource(id = R.drawable.ic_launcher_background),
                firstName = "Qazi Muhammad",
                lastName = "Huzaif",
                title = "Contributor",
                githubLink = "https://github.com/musaibshabir",
                linkedInLink = "https://www.linkedin.com/in/musaib-shabir-dar/",
                twitterLink = "https://twitter.com/musaib_shabir",
                instagramLink = "https://www.instagram.com/musaib.shabir/"
            )

            //Adnan Afzal
            DeveloperInfoCard(
                profilePicture = painterResource(id = R.drawable.ic_launcher_background),
                firstName = "Adnan",
                lastName = "Afzal",
                title = "Contributor",
                githubLink = "https://github.com/musaibshabir",
                linkedInLink = "https://www.linkedin.com/in/musaib-shabir-dar/",
                twitterLink = "https://twitter.com/musaib_shabir",
                instagramLink = "https://www.instagram.com/musaib.shabir/"
            )

            //Sofi Burhan
            DeveloperInfoCard(
                profilePicture = painterResource(id = R.drawable.profile_picture),
                firstName = "Sofi",
                lastName = "Burhan",
                title = "Contributor",
                githubLink = "https://github.com/musaibshabir",
                linkedInLink = "https://www.linkedin.com/in/musaib-shabir-dar/",
                twitterLink = "https://twitter.com/musaib_shabir",
                instagramLink = "https://www.instagram.com/musaib.shabir/"
            )
        }
    }
}

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun PreviewDeveloperInfoScreen() {
    DeveloperInfoScreen(navController = NavHostController(LocalContext.current))
}



