package com.example.foundit.presentation.screens.home


import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foundit.R
import com.example.foundit.presentation.screens.home.components.AppIcon
import com.example.foundit.presentation.screens.home.components.Greetings
import com.example.foundit.presentation.screens.home.components.MainCard
import com.example.foundit.presentation.screens.profile.ProfileViewModel
import com.example.foundit.ui.theme.MainGreen
import com.example.foundit.ui.theme.MainRed


// UI-Only Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    greetingPrefix: String,
    profileName: String?,
    navController: NavHostController,
    lostButtonClick: String,
    foundButtonClick: String,
) {
    Scaffold {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 20.dp, end = 20.dp),
            //horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppIcon(modifier = modifier)
            Greetings(
                modifier = modifier,
                greetingPrefix = greetingPrefix,
                profileName = profileName
            )
            HorizontalDivider(
                thickness = 1.dp,
                modifier = modifier
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            )
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(vertical = 20.dp)
                    .verticalScroll(rememberScrollState(), true),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                //Lost Card
                MainCard(
                    modifier = Modifier,
                    cardHeading = R.string.lost_card_heading,
                    cardTitle = R.string.lost_card_sub_title,
                    cardColor = MainRed,
                    navController = navController,
                    forwardNavigation = lostButtonClick,
                )

                //Found Card
                MainCard(
                    modifier = Modifier,
                    cardHeading = R.string.found_card_heading,
                    cardTitle = R.string.found_card_sub_title,
                    cardColor = MainGreen,
                    navController = navController,
                    forwardNavigation = foundButtonClick
                )
            }
        }
    }
}

// ViewModel Composable
@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: ProfileViewModel,
    navController: NavHostController,
    lostButtonClick: String,
    foundButtonClick: String,
) {
    val context = LocalContext.current
    val userFirstName by viewModel.userFirstNames.collectAsState()
    val userLastName by viewModel.userLastNames.collectAsState()
    val updateProfileData = viewModel.currentuserId != viewModel.previoususerId
    val greetingPrefix = stringResource(id = R.string.greeting_prefix)

    LaunchedEffect(updateProfileData) {
        viewModel.updateProfileData()
    }

    HomeScreenContent(
        modifier = modifier,
        greetingPrefix = greetingPrefix,
        profileName = "$userFirstName $userLastName",
        navController = navController,
        lostButtonClick = lostButtonClick,
        foundButtonClick = foundButtonClick
    )


    BackHandler(
        enabled = true,
        onBack = { (context as Activity).finish()  }
    )


}



@Composable
@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_2")
fun PreviewHomeScreen() {
    HomeScreenContent(
        modifier = Modifier,
        greetingPrefix = "HI",
        profileName = "Musaib Shabir",
        navController = NavHostController(LocalContext.current),
        lostButtonClick = "",
        foundButtonClick = ""
    )
}

