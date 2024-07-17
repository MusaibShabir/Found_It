package com.example.foundit.presentation.screens.registration.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ClickableTextToWebpage(text: String, url: String, modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current

    Text(
        text = text,
        textDecoration = TextDecoration.Underline,
        modifier = modifier
            .clickable { uriHandler.openUri(url) },
        color = Color.Blue,
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = false)
fun PreviewClickableTextToWebpage() {
    ClickableTextToWebpage(
        modifier = Modifier,
        text = "Terms of Service",
        url = "https://www.google.com"
    )
}

