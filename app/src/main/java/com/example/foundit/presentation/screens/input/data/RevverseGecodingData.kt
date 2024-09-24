package com.example.foundit.presentation.screens.input.data

import kotlinx.serialization.Serializable


@Serializable
data class GeocodingResponse(
    val results: List<Result> = emptyList(),
    val status: String
)

@Serializable
data class Result(
    val address_components: List<AddressComponent> = emptyList(),
    val formatted_address: String,
    val geometry: Geometry,
    val place_id: String,
    val types: List<String> = emptyList()
)

@Serializable
data class AddressComponent(
    val long_name: String,
    val short_name: String,
    val types: List<String> = emptyList()
)

@Serializable
data class Geometry(
    val location: Location,
    val location_type: String,
    val viewport: Viewport
)

@Serializable
data class Location(
    val lat: Double,
    val lng: Double
)

@Serializable
data class Viewport(
    val northeast: Location,
    val southwest: Location
)