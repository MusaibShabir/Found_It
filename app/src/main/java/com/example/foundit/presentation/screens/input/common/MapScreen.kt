package com.example.foundit.presentation.screens.input.common

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Looper
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.foundit.presentation.screens.input.lost.LostInputViewModel
import com.example.foundit.ui.theme.MainGreen
import com.example.foundit.ui.theme.MainRed
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(
    modifier: Modifier,
    cardType: Int?,
    viewModel: LostInputViewModel
) {
    val context = LocalContext.current
    val defaultLocation = LatLng(34.083658, 74.797373)

    val markerPosition by viewModel.markerPosition.collectAsState()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultLocation, 5.5f)
    }

    val locationPermissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

    )

    var mapTopHeading by remember { mutableStateOf("") }
    when (cardType) {
        0 -> mapTopHeading = "Pin the map area where you think you lost your item."
        1 -> mapTopHeading = "Pin Point the map location where you have found the item."
    }

    var mapRadius by remember { mutableDoubleStateOf(0.0) }
    when (cardType) {
        0 -> mapRadius = 2000.0
        1 -> mapRadius = 500.0
    }

    var mapRadiusColor by remember { mutableStateOf(Color.White) }
    when (cardType) {
        0 -> mapRadiusColor = MainRed
        1 -> mapRadiusColor = MainGreen
    }


    val locationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    var currentLocation by remember { mutableStateOf<LatLng?>(null) }

    // Location Callback for live location updates
    val locationCallback = remember {
        object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    currentLocation = LatLng(location.latitude, location.longitude)
                    viewModel.updateMarkerPosition(currentLocation!!)
                }
            }
        } // Return the LocationCallback object
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            OutlinedCard(
                modifier = modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Green.copy(alpha = .16f))
            ) {
                Row(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = mapTopHeading,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Justify,
                    )
                }
            }


        }


        HorizontalDivider(modifier = modifier.padding(vertical = 10.dp))

        val locationAddress by viewModel.formattedAddress.collectAsState()

        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location Icon",
                tint = MainGreen,
                modifier = modifier
                    .size(22.dp)
            )
            Spacer(modifier = modifier.width(6.dp))
            Text(
                text = locationAddress,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Start,
                textDecoration = TextDecoration.Underline,

            )
        }


        Spacer(modifier = modifier.height(8.dp))
        ElevatedCard(
            modifier = modifier
                .fillMaxSize()
                .padding(vertical = 18.dp)
        ) {

            if (locationPermissionsState.allPermissionsGranted) {
                LaunchedEffect(key1 = locationPermissionsState.allPermissionsGranted) {
                    val locationRequest =
                        LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000).apply {
                            setWaitForAccurateLocation(false)
                            setMinUpdateIntervalMillis(5000)
                            setMaxUpdateDelayMillis(10000)
                        }.build()

                    if (ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        locationClient.requestLocationUpdates(
                            locationRequest,
                            locationCallback,
                            Looper.getMainLooper()
                        )
                    } else {
                        // Handle the case where permissions are not granted (e.g., show a message)
                    }
                }

                GoogleMap(
                    modifier = modifier.fillMaxSize(),
                    properties = MapProperties(
                        isMyLocationEnabled = false,
                        isBuildingEnabled = true,
                        isIndoorEnabled = true,
                        isTrafficEnabled = true,
                    ),
                    cameraPositionState = cameraPositionState,
                    onMapClick = { latLng ->
                        viewModel.updateMarkerPosition(latLng)
                    },
                    onMapLongClick = {
                        viewModel.clearMarkerPosition()
                    },
                    onMyLocationButtonClick = {
                        currentLocation?.let {
                            CoroutineScope(Dispatchers.Main).launch {
                                cameraPositionState.animate(
                                    update = CameraUpdateFactory.newLatLngZoom(it, 13f),
                                    durationMs = 900
                                )
                            }
                        }
                        true
                    }
                ) {
                    // Trigger LaunchedEffect when marker position changes
                    markerPosition?.let { position ->
                        LaunchedEffect(position) {
                            viewModel.getMarkerAddressDetails(
                                position.latitude,
                                position.longitude,
                                context = context
                            )
                            cameraPositionState.animate(
                                update = CameraUpdateFactory.newLatLngZoom(position, 13f),
                                durationMs = 900
                            )
                        }


                        Marker(
                            state = MarkerState(position = position),
                            title = "Selected Location"
                        )

                        Circle(
                            center = position,
                            radius = mapRadius,
                            fillColor = mapRadiusColor.copy(alpha = 0.3f),
                            strokeColor = mapRadiusColor.copy(alpha = .6f),
                            strokeWidth = 5f,
                            clickable = false
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
                ) {
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
                    ) {
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
