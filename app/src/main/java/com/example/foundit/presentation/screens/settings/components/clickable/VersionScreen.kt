package com.example.foundit.presentation.screens.settings.components.clickable

import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foundit.presentation.common.TheTopAppBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun VersionScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val appVersion = appVersion(LocalContext.current)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TheTopAppBar(title = "Version", navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Text(text = appVersion, style = MaterialTheme.typography.bodyLarge)
        }

    }
}

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
                versionName = "Version not found"
            }
        }
    }
    return versionName
}

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun PreviewVersionScreen() {
    VersionScreen(
        navController = NavHostController(LocalContext.current)
    )
}