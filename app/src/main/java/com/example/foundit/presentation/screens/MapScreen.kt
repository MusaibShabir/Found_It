package com.example.foundit.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(-34.0, 151.0), 10f)
    }

    val currentLocation by locationViewModel.currentLocation.collectAsState()

    LaunchedEffect(Unit) {
        locationViewModel.getCurrentLocation()
    }

    GoogleMap(
        modifier = modifier.fillMaxSize(),
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
