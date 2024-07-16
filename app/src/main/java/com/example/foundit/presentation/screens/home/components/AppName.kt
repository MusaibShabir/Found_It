package com.example.foundit.presentation.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foundit.R

@Composable
fun AppName(modifier: Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .width(376.dp)
            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp),
        shape = RoundedCornerShape(size = 42.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp, hoveredElevation = 5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row (
            modifier = modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ){
            Text(
                text = stringResource(id = R.string.app_name),
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = false)
fun PreviewAppName() {
    AppName(
        modifier = Modifier,
    )
}