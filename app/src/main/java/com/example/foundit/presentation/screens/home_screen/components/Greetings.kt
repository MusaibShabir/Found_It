package com.example.foundit.presentation.screens.home_screen.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foundit.R
import kotlinx.coroutines.delay

@Composable
fun Greetings(
    modifier: Modifier,
    name: String
) {
    val greetingPrefix = stringResource(id = R.string.greeting_prefix)
    var isAnimationStarted by remember { mutableStateOf(false) }
    val animatedProgress = remember { Animatable(0f) }

    LaunchedEffect(key1 = name) {
        isAnimationStarted = true
        delay(1000)
        animatedProgress.animateTo(
            targetValue = 1f,
            animationSpec = keyframes {
                durationMillis = name.length * 73
                0.0f at 0 using LinearEasing
            })
    }

    val displayedText = if (isAnimationStarted) {
        greetingPrefix + " " + name.substring(0, (animatedProgress.value * name.length).toInt())
    } else {
        ""
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = displayedText,
                fontFamily = FontFamily(Font(resId = R.font.roboto_thin_italic)),
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                modifier = modifier.padding(start = 5.dp)
            )


        }



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