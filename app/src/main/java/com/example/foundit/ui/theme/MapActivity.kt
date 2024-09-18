package com.example.foundit.ui.theme

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : ComponentActivity(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    // Register the ActivityResultLauncher properly for permission request
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // Permission granted, initialize the map
                mapView.getMapAsync(this)
            } else {
                // Permission denied, show a message to the user
                Toast.makeText(this, "Location permission is required to use the map", Toast.LENGTH_LONG).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the FusedLocationProviderClient and MapView
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mapView = MapView(this)

        // Set the content to use Compose (if required) or the MapView
        setContent {
            // Compose UI content if any (or place the MapView inside the Compose content)
        }

        // Set up the MapView with the appropriate lifecycle methods
        mapView.onCreate(savedInstanceState)

        // Request location permission
        requestLocationPermission()
    }

    private fun requestLocationPermission() {
        when {
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
                // If permission is already granted, initialize the map
                mapView.getMapAsync(this)
            }
            else -> {
                // Always request permission
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        getLastLocation(googleMap)
    }

    private fun getLastLocation(googleMap: GoogleMap) {
        // Check permission again before accessing the last location
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    // Move camera to the user's location
                    val userLocation = LatLng(it.latitude, it.longitude)
                    googleMap.addMarker(MarkerOptions().position(userLocation).title("You are here"))
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15f))
                } ?: run {
                    // If location is null, show a message
                    Toast.makeText(this, "Unable to get location", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener { exception ->
                // Handle failure to get location
                exception.printStackTrace()
                Toast.makeText(this, "Failed to get location", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Location permission not granted", Toast.LENGTH_SHORT).show()
        }
    }

    // Properly handle the MapView lifecycle
    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}
