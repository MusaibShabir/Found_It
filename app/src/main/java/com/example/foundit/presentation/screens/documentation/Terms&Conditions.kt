package com.example.foundit.presentation.screens.documentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.foundit.presentation.common.TheTopAppBar


@Composable
fun TermsAndConditionsScreen(
    modifier: Modifier,
    onClick: String,
    navController: NavController
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TheTopAppBar(title = "Terms & Conditions", navController = navController)
        }
    ){
        Column(
            modifier = modifier.padding(it)
        ){

        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewTermsAndConditionsScreen() {
    TermsAndConditionsScreen(
        modifier = Modifier,
        onClick = "",
        navController = NavController(LocalContext.current)
    )
}