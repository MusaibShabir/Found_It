package com.example.foundit

<<<<<<< HEAD
=======
import android.annotation.SuppressLint
>>>>>>> b05e32c (Initial commit)
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
<<<<<<< HEAD
=======
import androidx.compose.foundation.layout.Column
>>>>>>> b05e32c (Initial commit)
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
<<<<<<< HEAD
import androidx.compose.ui.tooling.preview.Preview
import com.example.foundit.ui.theme.FoundItTheme

class MainActivity : ComponentActivity() {
=======
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foundit.components.AppName
import com.example.foundit.components.Greetings
import com.example.foundit.components.NavigationBar
import com.example.foundit.screens.HomeScreen
import com.example.foundit.screens.MainCard
import com.example.foundit.ui.theme.FoundItTheme
import com.example.foundit.ui.theme.MainGreen
import com.example.foundit.ui.theme.MainRed

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
>>>>>>> b05e32c (Initial commit)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoundItTheme {
<<<<<<< HEAD
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
=======
                HomeScreen(modifier = Modifier)
>>>>>>> b05e32c (Initial commit)
                }
            }
        }
    }
<<<<<<< HEAD
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
=======


>>>>>>> b05e32c (Initial commit)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoundItTheme {
<<<<<<< HEAD
        Greeting("Android")
=======
        MainActivity()
>>>>>>> b05e32c (Initial commit)
    }
}