package com.example.foundit.presentation.screens.registration.components
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.foundit.presentation.data.navigation.NavRoutes

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
        color = Color.Blue,
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