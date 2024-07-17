package com.example.foundit.presentation.screens.settings.components.clickable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.foundit.data.aboutOptionList
import com.example.foundit.presentation.common.TheTopAppBar
import com.example.foundit.presentation.screens.settings.components.SettingsOption

@Composable
fun AboutCard(
    modifier: Modifier = Modifier
){
    Column (
        modifier = modifier,
    ) {
        for (option in aboutOptionList)
            SettingsOption(option)
    }
}


@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar ={
            TheTopAppBar(title = "About", navController = navController)
        }
    ) {innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding),
        ) {
            AboutCard()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AboutScreenPreview(){
    AboutScreen(navController = NavHostController(LocalContext.current))
}