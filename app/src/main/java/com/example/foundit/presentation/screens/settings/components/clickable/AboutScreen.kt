package com.example.foundit.presentation.screens.settings.components.clickable

import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foundit.presentation.common.TheTopAppBar
import com.example.foundit.presentation.data.navigation.NavRoutes
import com.example.foundit.presentation.screens.settings.components.SettingsOptionCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar ={
            TheTopAppBar(title = "About", navController = navController)
        }
    ) {innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding),
        ) {
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Version", forwardNavigation = NavRoutes.VERSION, navController = navController)
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Privacy Policy", forwardNavigation = NavRoutes.PRIVACY_POLICY, navController = navController)
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Acknowledgments", forwardNavigation = NavRoutes.ACKNOWLEDGMENTS, navController = navController)
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Developer Information", forwardNavigation = NavRoutes.DEVELOPER_INFO, navController = navController)
            SettingsOptionCard(modifier = modifier, settingsOptionName = "Follow us", forwardNavigation = NavRoutes.FOLLOW_US, navController = navController)

            Spacer(modifier = modifier.height(15.dp))

            val appVersion = appVersion(LocalContext.current)
            val versionPrefix = "Version:"
            var isAnimationStarted by remember { mutableStateOf(false) }
            val animatedProgress = remember { Animatable(0f) }

            LaunchedEffect(key1 = appVersion) {
                isAnimationStarted = true
                delay(1000)
                animatedProgress.animateTo(
                    targetValue = 1f,
                    animationSpec = keyframes {
                        durationMillis = appVersion.length * 73
                        0.0f at 0 using LinearEasing
                    })
            }

            val displayedText = if (isAnimationStarted) {
                "$versionPrefix " + appVersion.substring(0, (animatedProgress.value * appVersion.length).toInt())
            } else {
                ""
            }

            Row (
                modifier = modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ){

                Text(
                    text = displayedText,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}


//Helper Function to Retrieve App Version from the Builder File
@Composable
fun appVersion(context: Context): String {
    var versionName by remember { mutableStateOf("") }
    LaunchedEffect(key1 = context) {
        withContext(Dispatchers.IO) {
            try {
                val packageManager = context.packageManager
                val info = packageManager.getPackageInfo(context.packageName, 0)
                versionName = info.versionName
            } catch (e: PackageManager.NameNotFoundException) {
                versionName = "X.XX.XX"
            }
        }
    }
    return versionName
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AboutScreenPreview(){
    AboutScreen(navController = NavHostController(LocalContext.current))
}