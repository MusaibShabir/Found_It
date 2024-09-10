package com.example.foundit.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foundit.R

@Composable
fun AppIcon(
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 10.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Image(
            painter = painterResource(R.drawable.app_name_icon),
            contentDescription = "App Logo",
            modifier = modifier
        )
    }


}

@Composable
@Preview(showBackground = true, showSystemUi = false)
fun PreviewAppName() {
    AppIcon(
        modifier = Modifier,
    )
}