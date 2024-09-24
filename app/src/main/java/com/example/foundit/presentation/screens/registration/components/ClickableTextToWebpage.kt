package com.example.foundit.presentation.screens.registration.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.foundit.presentation.data.navigation.NavRoutes
import com.example.foundit.ui.theme.MainGreen

@Composable
fun ClickableTextToNavigationRoute(
    text: String,
    navRoute: String,
    navController: NavController,
    modifier: Modifier = Modifier
) {
        Text(
            text = text,
            textDecoration = TextDecoration.Underline,
            modifier = modifier
                .clickable { navController.navigate(navRoute) },
            color = MainGreen,
        )
}



@Composable
@Preview(showBackground = true, showSystemUi = false)
fun PreviewClickableTextToWebpage() {
    ClickableTextToNavigationRoute(
        modifier = Modifier,
        text = "Terms of Service",
        navRoute = NavRoutes.HOME,
        navController = NavHostController(LocalContext.current)
    )
}

