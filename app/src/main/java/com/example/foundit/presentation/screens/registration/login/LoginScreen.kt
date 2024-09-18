package com.example.foundit.presentation.screens.registration.login

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.foundit.presentation.screens.registration.components.google.ContinueWithGoogleViewModel
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
@Composable
fun LoginScreen(
    modifier: Modifier,
    loginViewModel: LoginViewModel,
    continueWithGoogleViewModel: ContinueWithGoogleViewModel,
    navController: NavController
) {
    val email by remember { mutableStateOf("") }
    val password by remember { mutableStateOf("") }

    // For Validation
    var isEmailValid by remember { mutableStateOf(true) }
    var isPasswordValid by remember { mutableStateOf(true) }

    // For location permission
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Fetch location once permission is granted
            coroutineScope.launch {
                getLastLocation(fusedLocationClient, context)
            }
        } else {
            Toast.makeText(context, "Location permission is required to proceed", Toast.LENGTH_LONG).show()
        }
    }

    // This method gets called after successful login
    fun requestLocationPermission() {
        when {
            ContextCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                coroutineScope.launch {
                    getLastLocation(fusedLocationClient, context)
                }
            }
            else -> {
                locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Top
    ) {
        // [Content UI, Login Form, etc.]

        // Login Button
        ElevatedButton(
            modifier = modifier
                .width(200.dp)
                .height(52.dp),
            onClick = {
                try {
                    loginViewModel.login(email, password) { isSuccess ->
                        if (isSuccess) {
                            Log.d("Login", "Login successful")
                            // Trigger permission request after successful login
                            requestLocationPermission()
                        } else {
                            Log.d("Login", "Authentication failed")
                        }
                    }
                } catch (e: Exception) {
                    Log.d("Login", "error (login screen) $e")
                }
            },
            // [Other Button Properties]
        ) {
            Text(text = "LOGIN")
        }

        // [Other UI components, Google login, Sign-up link, etc.]
    }
}

// Helper function to get location
suspend fun getLastLocation(
    fusedLocationClient: FusedLocationProviderClient,
    context: Context
) {
    // Check if location permission is granted before accessing the location
    if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
        == PackageManager.PERMISSION_GRANTED
    ) {
        try {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    val userLocation = LatLng(it.latitude, it.longitude)
                    // Do something with the location
                    Log.d("Location", "User is at: $userLocation")
                } ?: run {
                    Toast.makeText(context, "Turn on Location(GPS)", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener { exception ->
                exception.printStackTrace()
                Toast.makeText(context, "Failed to get location", Toast.LENGTH_SHORT).show()
            }
        } catch (e: SecurityException) {
            Log.e("Location", "Permission denied: ${e.message}")
        }
    } else {
        Toast.makeText(context, "Location permission not granted", Toast.LENGTH_SHORT).show()
    }
}