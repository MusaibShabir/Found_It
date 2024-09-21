package com.example.foundit.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.tasks.await

object LocationUtils {

    private const val LOCATION_PERMISSION_REQUEST_CODE = 1001


    // Function to check for fine location permissions
    fun checkAndRequestLocationPermission(activity: Activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Request precise location permission
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    // Function to handle the permission result
    fun handlePermissionResult(
        requestCode: Int,
        grantResults: IntArray,
        onPermissionGranted: () -> Unit,
        onPermissionDenied: () -> Unit
    ) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                onPermissionGranted()
            } else {
                // Permission denied
                onPermissionDenied()
            }
        }
    }

    // Function to get user's location and update the Google Map
    suspend fun getUserLocation(context: Context, googleMap: GoogleMap?) {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        // Ensure location permission is granted
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        try {
            val location = fusedLocationClient.lastLocation.await()
            location?.let {
                val userLocation = LatLng(it.latitude, it.longitude)
                googleMap?.apply {
                    addMarker(MarkerOptions().position(userLocation).title("You are here"))
                    moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15f))
                }
            } ?: run {
                Toast.makeText(context, "Unable to fetch location. Turn on GPS.", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Failed to retrieve location", Toast.LENGTH_SHORT).show()
        }
    }

    fun startLocationUpdates(
        context: Context,
        googleMap: GoogleMap?,
        locationRequest: LocationRequest,
        locationCallback: LocationCallback
    ) {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        // Ensure location permission is granted
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(context, "Precise location permission required", Toast.LENGTH_SHORT).show()
            return
        }

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }
}
