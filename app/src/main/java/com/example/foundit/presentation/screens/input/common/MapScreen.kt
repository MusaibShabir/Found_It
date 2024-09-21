package com.example.foundit.presentation.screens.input.common

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(modifier: Modifier) {

    val defaultLocation = LatLng(34.083658, 74.797373)

    val markerPosition = remember { mutableStateOf<LatLng?>(null) }

    val cameraPositionState = rememberCameraPositionState{
        position = CameraPosition.fromLatLngZoom(defaultLocation, 5.5f)
    }

    val locationPermissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

    )


    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        ElevatedCard(
            modifier = modifier
                .fillMaxWidth()
                .padding(32.dp)
                .height(430.dp)
        ) {

            if (locationPermissionsState.allPermissionsGranted) {
                GoogleMap(
                    modifier = modifier.fillMaxSize(),
                    properties = MapProperties(
                        isMyLocationEnabled = true,
                        isBuildingEnabled = true,
                        isIndoorEnabled = true,
                        isTrafficEnabled = true,
                    ),
                    uiSettings = MapUiSettings(zoomControlsEnabled = true),
                    cameraPositionState = cameraPositionState,
                    onMapClick = { latLng ->
                        markerPosition.value = latLng
                        CoroutineScope(Dispatchers.Main).launch {
                            cameraPositionState.animate(
                                update = CameraUpdateFactory.newLatLngZoom(latLng, 13f),
                                durationMs = 900
                            )
                        }
                    }
                ) {
                    markerPosition.value?.let { position ->
                        Marker(
                            state = MarkerState(position = position),
                            title = "Marker",

                            )
                        Circle(
                            center = position,
                            radius = 2000.0, // Radius in meters
                            fillColor = Color.Red.copy(alpha = 0.3f),
                            strokeColor = Color.Red,
                            strokeWidth = 3f
                        )
                    }

                }
            } else {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentSize()
                        .padding(horizontal = 26.dp, vertical = 33.dp),
                        //.padding(top = 102.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    val allPermissionsRevoked =
                        locationPermissionsState.permissions.size ==
                                locationPermissionsState.revokedPermissions.size

                    val textToShow = if (!allPermissionsRevoked) {
                        "Yay! Thanks for letting me access your approximate location. " +
                                "But, could you please let me access your precise location?"
                    } else if (locationPermissionsState.shouldShowRationale) {
                        "Location permissions are important for this app. Please grant them."
                    } else {
                        "Location permissions are necessary to run this operation, they are permanently denied at the moment. " +
                                "You can enable them below or in the app settings."
                    }

                    Text(
                        text = textToShow,
                        textAlign = TextAlign.Center
                    )

                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.Center
                    ){
                        Button(
                            onClick = { locationPermissionsState.launchMultiplePermissionRequest() },
                        ) {
                            Text("Request permissions")
                        }
                    }

                }

            }
        }
    }


}

