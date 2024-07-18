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
fun AccountCenterCard(
    modifier: Modifier = Modifier
){
    Column (
        modifier = modifier,
    ) {

    }
}


@Composable
fun AccountCenterScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar ={
            TheTopAppBar(title = "Account Center", navController = navController)
        }
    ) {innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding),
        ) {
            AccountCenterCard()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AccountCenterScreenPreview(){
    AccountCenterScreen(navController = NavHostController(LocalContext.current))
}