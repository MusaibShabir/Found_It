package com.example.foundit.maps

import android.location.Location
import com.example.foundit.maps.clusters.ZoneClusterItem

data class MapState(
    val lastKnownLocation: Location?,
    val clusterItems: List<ZoneClusterItem>,
)
