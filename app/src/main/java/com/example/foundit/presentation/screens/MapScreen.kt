package com.example.foundit.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.Marker
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapScreen(modifier: Modifier = Modifier) {
    // Remember the camera position state
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(-34.0, 151.0), 10f)
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        properties = MapProperties(),
        uiSettings = MapUiSettings()
    ) {
        // Add a marker
        val markerState = rememberMarkerState(position = LatLng(-34.0, 151.0))
        Marker(
            state = markerState,
            title = "Sydney",
            snippet = "Australia"
        )
    }
}
