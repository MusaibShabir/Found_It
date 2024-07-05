package com.example.foundit.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foundit.R

@Composable
fun Greetings(
    modifier: Modifier,
    name: String
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = "${stringResource(id = R.string.greeting_prefix)} $name",
            fontFamily = FontFamily(Font(resId = R.font.roboto_thin_italic)),
            fontSize = 24.sp,
            maxLines = 1

        )
    }

}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewGreetings() {
    Greetings(
        modifier = Modifier,
        name = "Musaib"
    )
}