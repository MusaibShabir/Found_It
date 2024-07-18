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
import com.example.foundit.presentation.common.TheTopAppBar

@Composable
fun HelpAndSupportCard(
    modifier: Modifier = Modifier
){
    Column (
        modifier = modifier,
    ) {

    }
}


@Composable
fun HelpAndSupportScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar ={
            TheTopAppBar(title = "Help and Support", navController = navController)
        }
    ) {innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding),
        ) {
            HelpAndSupportCard()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHelpAndSupportScreen(){
    HelpAndSupportScreen(navController = NavHostController(LocalContext.current))
}