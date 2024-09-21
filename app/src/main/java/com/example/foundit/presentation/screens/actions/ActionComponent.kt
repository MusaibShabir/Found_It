
package com.example.foundit.presentation.screens.actions

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.foundit.presentation.common.TheTopAppBar
import com.example.foundit.presentation.data.addCard
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.foundit.utils.LocationUtils
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionComponent(
    modifier: Modifier,
    navController: NavHostController
) {
    val viewModel: ActionComponentViewModel = hiltViewModel()
    val context = LocalContext.current
    val activity = LocalContext.current as Activity //Map permission
    var userLocation by remember { mutableStateOf<LatLng?>(null) }
    var showPermissionRationale by remember { mutableStateOf(false) }
    var locationPermissionGranted by remember { mutableStateOf(false) }


    var selectedPhone by remember { mutableStateOf("") }
    var selectedModel by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf("") }

    var phoneExpanded by remember { mutableStateOf(false) }
    var modelExpanded by remember { mutableStateOf(false) }
    var colorExpanded by remember { mutableStateOf(false) }

    val phoneOptions = listOf("Phone 1", "Phone 2", "Phone 3")
    val modelOptions = listOf("Model A", "Model B", "Model C")
    val colorOptions = listOf("Red", "Green", "Blue")
    var descriptionEntered by remember { mutableStateOf("") }

    //Map and location variables
    val mapView = remember { MapView(context) }
    var googleMap by remember { mutableStateOf<GoogleMap?>(null) }

// Location request and callback
    val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000).apply {
        setMinUpdateIntervalMillis(5000)
    }.build()

    val locationCallback = remember {
        object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.locations.forEach { location ->
                    val latLng = LatLng(location.latitude, location.longitude)
                    userLocation = latLng
                    googleMap?.apply {
                        moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                        addMarker(MarkerOptions().position(latLng).title("Updated Location"))
                    }
                }
            }
        }
    }

    // Request location permissions
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Toast.makeText(context, "Locating...", Toast.LENGTH_SHORT).show()
            locationPermissionGranted = true
        } else {
            showPermissionRationale = ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            if (!showPermissionRationale) {
                // User has permanently denied the permission
                Toast.makeText(context, "Location permission permanently denied", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Location permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }


    LaunchedEffect(Unit) {
        // Check permission status and show rationale if needed
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) -> {
                locationPermissionGranted = true
            }
            else -> {
                showPermissionRationale = ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
                launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    // Trigger location updates when permission is granted
    LaunchedEffect(locationPermissionGranted) {
        if (locationPermissionGranted) {
            googleMap?.let { map ->
                LocationUtils.getUserLocation(context, map)
            }
        }
    }

    LaunchedEffect(googleMap) {
        googleMap?.let { map ->
            LocationUtils.getUserLocation(context, map)
            LocationUtils.startLocationUpdates(context, map, locationRequest, locationCallback)
        }
    }

    DisposableEffect(Unit) {
        // Start location updates when the map is available
        googleMap?.let {
            LocationUtils.startLocationUpdates(context, it, locationRequest, locationCallback)
        }
        onDispose {
            // Stop location updates if necessary
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val mapViewLifecycleObserver = object : LifecycleObserver {
            fun onStart() {
                mapView.onStart()
            }

            fun onResume() {
                mapView.onResume()
            }

            fun onPause() {
                mapView.onPause()
            }

            fun onStop() {
                mapView.onStop()
            }

            fun onDestroy() {
                mapView.onDestroy()
            }

            fun onLowMemory() {
                mapView.onLowMemory()
            }
        }

        lifecycleOwner.lifecycle.addObserver(mapViewLifecycleObserver)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(mapViewLifecycleObserver)
        }
    }


    Column(
        modifier = modifier
            .padding(14.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Existing Phone, Model, and Color Dropdowns
        ExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth(),
            expanded = phoneExpanded,
            onExpandedChange = { phoneExpanded = !phoneExpanded }
        ) {
            OutlinedTextField(
                readOnly = true,
                value = selectedPhone,
                onValueChange = {},
                label = { Text("Phone") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = phoneExpanded) },
                placeholder = { Text("Select Phone", fontStyle = FontStyle.Italic) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp)
            )

            ExposedDropdownMenu(
                expanded = phoneExpanded,
                onDismissRequest = { phoneExpanded = false }
            ) {
                phoneOptions.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            selectedPhone = selectionOption
                            phoneExpanded = false
                        }
                    )
                }
            }
        }

        // MapView for Location
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(bottom = 18.dp),
            factory = { context ->
                mapView.apply {
                    onCreate(Bundle())
                    getMapAsync { gMap ->
                        googleMap = gMap // Store the reference
                        userLocation?.let { location ->
                            gMap.addMarker(MarkerOptions().position(location).title("You are here"))
                            gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12.5f))
                        } ?: run {
                            // Default location if userLocation is null
                            val defaultLocation = LatLng(34.0712981, 74.8105518)
                            gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 12.5f))
                        }
                    }
                }
            }
        )

        // Description field
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 18.dp),
            value = descriptionEntered,
            onValueChange = { descriptionEntered = it },
            label = { Text("Description") },
            placeholder = { Text("Enter Description", fontStyle = FontStyle.Italic) },
            shape = MaterialTheme.shapes.medium,
            maxLines = 3,
        )

        // Buttons for Submit and Cancel
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Cancel")
            }

            Button(
                onClick = {
                    addCard(
                        cardColorCode = 0,
                        itemTitle = selectedPhone,
                        itemDescription = descriptionEntered,
                        itemLocation = userLocation.toString(),
                        progressIndicator = true
                    )
                    viewModel.sendData(selectedPhone, selectedModel, selectedColor) { isSuccess ->
                        if (isSuccess) {
                            Toast.makeText(context, "Item added!", Toast.LENGTH_LONG).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "Error!", Toast.LENGTH_LONG).show()
                        }
                    }
                },
                enabled = selectedPhone.isNotEmpty() && selectedModel.isNotEmpty() && selectedColor.isNotEmpty()
            ) {
                Text(text = "Submit")
            }
        }
    }
}

@Composable
fun ActionScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TheTopAppBar(
                title = "Report Item",
                navController = navController,
            )
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .padding(innerPadding),
        ) {
            ActionComponent(modifier, navController)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewActionScreen() {
    ActionScreen(
        modifier = Modifier,
        navController = NavHostController(LocalContext.current)
    )
}
