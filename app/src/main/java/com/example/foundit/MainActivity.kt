package com.example.foundit


import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.foundit.ui.theme.screens.HomeScreen
import com.example.foundit.ui.theme.screens.MainScreen
import com.example.foundit.ui.theme.FoundItTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setFlags(
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS,
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS
            )
        }
        window.statusBarColor = Color.TRANSPARENT
        setContent {
            FoundItTheme {
                MainScreen(modifier = Modifier)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoundItTheme {
        HomeScreen(modifier = Modifier)
    }
}