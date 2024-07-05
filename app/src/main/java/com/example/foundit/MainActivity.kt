package com.example.foundit


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.foundit.screens.HomeScreen
import com.example.foundit.screens.MainScreen
import com.example.foundit.ui.theme.FoundItTheme



class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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