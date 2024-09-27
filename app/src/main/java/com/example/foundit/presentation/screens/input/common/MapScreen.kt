package com.example.foundit.presentation.screens.input.common

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
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
import androidx.navigation.NavController
import com.example.foundit.presentation.screens.input.lost.LostInputViewModel
import com.example.foundit.ui.theme.MainGreen
import com.example.foundit.ui.theme.MainRed
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
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
import java.lang.ref.WeakReference


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(
    modifier: Modifier,
    viewModel: LostInputViewModel,
    navController: NavController,
) {

    val cardType = navController.currentBackStackEntry?.arguments?.getInt("cardType")
    val context = LocalContext.current
    val defaultLocation = LatLng(34.083658, 74.797373)

    val markerPosition by viewModel.markerPosition.collectAsState()
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultLocation, 5.5f)
    }

    val locationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    var currentLocation by remember { mutableStateOf<LatLng?>(null) }

    // LocationManager for checking GPS status
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    var isGpsEnabled by remember { mutableStateOf(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) }

    val locationSettingsChangeLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    DisposableEffect(Unit) {

        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == LocationManager.PROVIDERS_CHANGED_ACTION) {
                    isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                }
            }
        }
        context.registerReceiver(receiver, IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION))

        onDispose {
            val weakContext = WeakReference(context)
            weakContext.get()?.unregisterReceiver(receiver)
        }
    }

    val locationPermissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
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
        1 -> mapRadius = 50.0
    }

    var zoomAfterTap by remember { mutableFloatStateOf(0f) }
    when (cardType) {
        0 -> zoomAfterTap = 13f
        1 -> zoomAfterTap = 18f
    }

    var mapRadiusColor by remember { mutableStateOf(Color.White) }
    when (cardType) {
        0 -> mapRadiusColor = MainRed
        1 -> mapRadiusColor = MainGreen
    }

    // Location Callback for live location updates
    val locationCallback = remember {
        object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    currentLocation = LatLng(location.latitude, location.longitude)
                }
            }
        }
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
                colors = CardDefaults.cardColors(containerColor = MainGreen)
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
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                    )
                }
            }


        }


        HorizontalDivider(modifier = modifier.padding(vertical = 10.dp))

        val locationAddress by viewModel.address.collectAsState()

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
            locationAddress?.let {
                Text(
                    text = it,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Start,
                    textDecoration = TextDecoration.Underline,

                    )
            }
        }


        Spacer(modifier = modifier.height(8.dp))
        OutlinedCard(
            modifier = modifier
                .fillMaxSize()
            ,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
            border = BorderStroke(width = 1.dp, color = MainGreen)
        ) {
            if (locationPermissionsState.allPermissionsGranted && isGpsEnabled  ) {
                LaunchedEffect(locationPermissionsState.allPermissionsGranted, isGpsEnabled) {
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
                    }
                }

                GoogleMap(
                    modifier = modifier.fillMaxSize(),
                    properties = MapProperties(
                        isMyLocationEnabled = true,
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
                    markerPosition?.let { position ->
                        LaunchedEffect(position) {
                            if (viewModel.isNetworkAvailableViewmodel(context)) {
                                viewModel.fetchAddressFromGeocodingApi(
                                    position.latitude,
                                    position.longitude,
                                )
                                cameraPositionState.animate(
                                    update = CameraUpdateFactory.newLatLngZoom(
                                        position, zoomAfterTap),
                                    durationMs = 900
                                )
                            } else {
                                Toast.makeText(
                                    context,
                                    "No Internet Connection",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

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
                        .fillMaxSize()
                        .padding(horizontal = 26.dp, vertical = 33.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    val fineLocationPermissionState = locationPermissionsState.permissions.find {
                        it.permission == Manifest.permission.ACCESS_FINE_LOCATION
                    }


                    val permissionRequestText = when {
                        fineLocationPermissionState?.status?.isGranted == true
                            -> "PLease Enable GPS Location below."
                        !locationPermissionsState.allPermissionsGranted &&
                                locationPermissionsState.permissions.any {
                                    it.permission == Manifest.permission.ACCESS_COARSE_LOCATION && it.status.isGranted
                                } -> "Approximate location access granted. " +
                                "This app works best with precise location. " +
                                "Please  allow access to precise location below"
                        locationPermissionsState.shouldShowRationale ->
                            "Location permissions are important for this app. Please grant them."
                        else -> "Location permissions are necessary to use this feature. You can grant them below."
                    }

                    Text(
                        text = permissionRequestText,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier.height(16.dp))

                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        if (fineLocationPermissionState?.status?.isGranted == false) {
                            ElevatedButton(
                                colors = ButtonDefaults.elevatedButtonColors(
                                    containerColor = MainGreen,
                                    contentColor = Color.White
                                ),
                                onClick = {
                                    locationPermissionsState.launchMultiplePermissionRequest()
                                }
                            ) {
                                Text("Request Permissions")
                            }
                        }

                        if (!isGpsEnabled) {
                            ElevatedButton(
                                colors = ButtonDefaults.elevatedButtonColors(
                                    containerColor = MainGreen,
                                    contentColor = Color.White
                                ),
                                onClick = {
                                    locationSettingsChangeLauncher.launch(
                                        Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                                    )
                                }
                            ) {
                                Text("Enable GPS")
                            }
                        }
                    }
                }
            }
        }
    }
}

