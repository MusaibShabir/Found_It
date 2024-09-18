    package com.example.foundit.ui.theme

    import android.content.Intent
    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.compose.material3.*
    import androidx.compose.runtime.*

    class LoginActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                FoundItTheme {
                    // Simple login UI
                    LoginScreen(onLoginSuccess = { navigateToMapActivity() })
                }
            }
        }

        private fun navigateToMapActivity() {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    @Composable
    fun LoginScreen(onLoginSuccess: () -> Unit) {
        // Implement your login UI here
        // Call onLoginSuccess() when login is successful
        Button(onClick = { onLoginSuccess() }) {
            Text("Login")
        }
    }
