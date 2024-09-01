package com.example.foundit.presentation.screens.settings.components.clickable.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
                .verticalScroll(rememberScrollState())
        ) {

            //Musaib Shabir
            DeveloperInfoCard(
                profilePicture = painterResource(id = R.drawable.musaib_shabir_dp),
                firstName = "Musaib",
                lastName = "Shabir",
                title = "Team Lead & Senior Developer",
                githubLink = "https://github.com/musaibshabir",
                linkedInLink = "https://www.linkedin.com/in/musaib-shabir-49179b244/",
                twitterLink = "https://twitter.com/musaib_shabir",
                instagramLink = "https://www.instagram.com/musaib.shabir/"
            )

            //Qazi Huzaif
            DeveloperInfoCard(
                profilePicture = painterResource(id = R.drawable.qazi_huzaif_dp),
                firstName = "Qazi Muhammad",
                lastName = "Huzaif",
                title = "Contributor",
                githubLink = "https://github.com/qaziHuzaif",
                linkedInLink = "https://www.linkedin.com/in/qazi-huzaif-4675211b4/",
                twitterLink = "https://x.com/_QaziHuzaif?t=EG_z9ySDH8ibDyTSX6z3Uw&s=09",
                instagramLink = "https://www.instagram.com/qazi_huzaif?igsh=OGQ5ZDc2ODk2ZA=="
            )

            //Sofi Burhan
            DeveloperInfoCard(
                profilePicture = painterResource(id = R.drawable.profile_picture),
                firstName = "Sofi",
                lastName = "Burhan",
                title = "Contributor",
                githubLink = "https://github.com/SofiBurhonAhmad",
                linkedInLink = "https://www.linkedin.com/in/sofi-burhon-ahmad-b41426228/",
                twitterLink = "https://x.com/SBurhan09",
                instagramLink = "https://www.instagram.com/sofi__burhan_/?hl=en"
            )

            //Adnan Afzal
            DeveloperInfoCard(
                profilePicture = painterResource(id = R.drawable.adnan_afzal_dp),
                firstName = "Adnan",
                lastName = "Afzal",
                title = "Contributor",
                githubLink = "https://github.com/Adnan-Afzal",
                linkedInLink = "https://www.linkedin.com/in/adnanafzal01/",
                twitterLink = "https://x.com/Adnan___Afzal?t=AoFK8xT-m1WR8NcnkmJpdQ&s=09",
                instagramLink = "https://www.instagram.com/adnan._.afzal?igsh=dXMwMjdnYjN1Mjhp"
            )
        }
    }
}

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun PreviewDeveloperInfoScreen() {
    DeveloperInfoScreen(
        navController = NavHostController(LocalContext.current))
}



