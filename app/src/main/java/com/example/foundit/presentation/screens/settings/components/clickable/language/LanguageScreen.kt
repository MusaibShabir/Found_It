package com.example.foundit.presentation.screens.settings.components.clickable.language

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
fun LanguageScreen(
    modifier: Modifier,
    navController: NavController
){
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar ={
            TheTopAppBar(title = "Language", navController = navController)
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLanguageScreen(){
    LanguageScreen(modifier = Modifier, navController = NavController(LocalContext.current))
}