package com.example.foundit.presentation.ui.components

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
fun RequestLocationPermission() {
    val context = LocalContext.current
    val permissionState = remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        permissionState.value = isGranted
        if (isGranted) {
            Toast.makeText(context, "Location Permission Granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Location Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    Button(onClick = {
        launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }) {
        Text(text = "Request Location Permission")
    }

    if (permissionState.value) {
        Text(text = "Permission Granted")
    } else {
        Text(text = "Permission Required")
    }
}
