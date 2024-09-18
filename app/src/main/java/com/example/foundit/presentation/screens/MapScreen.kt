package com.example.foundit.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.example.foundit.presentation.viewmodel.LocationViewModel
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

@Composable
fun MapScreen(modifier: Modifier = Modifier, locationViewModel: LocationViewModel = viewModel()) {
    // Define the dimensions for the horizontally placed rectangle
    val mapModifier = modifier
        .width(320.dp)  // Width of the rectangle
        .height(180.dp) // Height of the rectangle

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(34.0712956, 74.8105561), 11.5f)
    }

    val currentLocation by locationViewModel.currentLocation.collectAsState()

    LaunchedEffect(Unit) {
        locationViewModel.getCurrentLocation()
    }

    GoogleMap(
        modifier = mapModifier, // Apply the modified size
        cameraPositionState = cameraPositionState,
        properties = MapProperties(),
        uiSettings = MapUiSettings()
    ) {
        currentLocation?.let {
            val markerState = rememberMarkerState(position = it)
            Marker(
                state = markerState,
                title = "Current Location"
            )
            cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 15f)
        }
    }
}
