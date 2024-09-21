package com.example.foundit.presentation.screens.input.common

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foundit.ui.theme.MainGreen
import com.example.foundit.ui.theme.MainRed
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
fun MapScreen(
    modifier: Modifier,
    cardType: Int?
) {

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

    var mapTopHeading by remember { mutableStateOf("") }
    when(cardType) {
        0 -> mapTopHeading = "Pin Point the map location where you think you lost your item."
        1 -> mapTopHeading = "Pin Point the map location where you have found the item."
    }

    var mapRadius by remember { mutableDoubleStateOf(0.0)}
    when (cardType) {
        0 -> mapRadius = 2000.0
        1 -> mapRadius = 500.0
    }

    var mapRadiusColor by remember { mutableStateOf(Color.White) }
    when(cardType) {
        0 -> mapRadiusColor = MainRed
        1 -> mapRadiusColor = MainGreen
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Green.copy(alpha = .16f))
            ){
                Row(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = mapTopHeading,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Justify,
                    )  }
                }


        }

        Spacer(modifier = modifier.height(8.dp))

        HorizontalDivider()

        Spacer(modifier = modifier.height(16.dp))

        ElevatedCard(
            modifier = modifier
                .fillMaxWidth()
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
                        CoroutineScope(Dispatchers.Main ).launch {
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
                            radius = mapRadius, // Radius in meters
                            fillColor = mapRadiusColor.copy(alpha = 0.3f),
                            strokeColor = mapRadiusColor,
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

