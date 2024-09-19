package com.example.foundit.presentation.screens.actions

import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.foundit.presentation.common.TheTopAppBar
import com.example.foundit.presentation.data.addCard
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.compose.LocalLifecycleOwner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionComponent(
    modifier: Modifier,
    navController: NavHostController
) {
    val viewModel: ActionComponentViewModel = hiltViewModel()
    val context = LocalContext.current

    var selectedPhone by remember { mutableStateOf("") }
    var selectedModel by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf("") }

    var phoneExpanded by remember { mutableStateOf(false) }
    var modelExpanded by remember { mutableStateOf(false) }
    var colorExpanded by remember { mutableStateOf(false) }

    val phoneOptions = listOf("Phone 1", "Phone 2", "Phone 3")
    val modelOptions = listOf("Model A", "Model B", "Model C")
    val colorOptions = listOf("Red", "Green", "Blue")

    var locationEntered by remember { mutableStateOf("") }
    var descriptionEntered by remember { mutableStateOf("") }

    val mapView = remember { MapView(context) }
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(mapView) {
        mapView.onCreate(Bundle())
        mapView.getMapAsync { googleMap ->
            val defaultLocation = LatLng(34.0712959, 74.8105467)
            googleMap.addMarker(MarkerOptions().position(defaultLocation).title("Ghanta Ghar"))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 12.5f))
        }
    }

    DisposableEffect(lifecycleOwner) {
        val mapViewLifecycleObserver = object : LifecycleObserver {
            fun onStart() {
                mapView.onStart()
            }

            fun onResume() {
                mapView.onResume()
            }

            fun onPause() {
                mapView.onPause()
            }

            fun onStop() {
                mapView.onStop()
            }

            fun onDestroy() {
                mapView.onDestroy()
            }

            fun onLowMemory() {
                mapView.onLowMemory()
            }
        }

        lifecycleOwner.lifecycle.addObserver(mapViewLifecycleObserver)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(mapViewLifecycleObserver)
        }
    }

    Column(
        modifier = modifier
            .padding(14.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Existing Phone, Model, and Color Dropdowns
        ExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth(),
            expanded = phoneExpanded,
            onExpandedChange = { phoneExpanded = !phoneExpanded }
        ) {
            OutlinedTextField(
                readOnly = true,
                value = selectedPhone,
                onValueChange = {},
                label = { Text("Phone") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = phoneExpanded) },
                placeholder = { Text("Select Phone", fontStyle = FontStyle.Italic) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp)
            )

            ExposedDropdownMenu(
                expanded = phoneExpanded,
                onDismissRequest = { phoneExpanded = false }
            ) {
                phoneOptions.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            selectedPhone = selectionOption
                            phoneExpanded = false
                        }
                    )
                }
            }
        }

        // Model and Color dropdowns (similar to the Phone dropdown)

        // MapView for Location
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(bottom = 18.dp),
            factory = { context ->
                mapView.apply {
                    onCreate(Bundle())
                    getMapAsync { googleMap ->
                        val defaultLocation = LatLng(0.0, 0.0)
                        googleMap.addMarker(MarkerOptions().position(defaultLocation).title("Location"))
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 12.5f))
                    }
                }
            }
        )

        // Description field
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 18.dp),
            value = descriptionEntered,
            onValueChange = { descriptionEntered = it },
            label = { Text("Description") },
            placeholder = { Text("Enter Description", fontStyle = FontStyle.Italic) },
            shape = MaterialTheme.shapes.medium,
            maxLines = 3,
        )

        // Buttons for Submit and Cancel
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Cancel")
            }

            Button(
                onClick = {
                    addCard(
                        cardColorCode = 0,
                        itemTitle = selectedPhone,
                        itemDescription = descriptionEntered,
                        itemLocation = locationEntered,
                        progressIndicator = true
                    )
                    viewModel.sendData(selectedPhone, selectedModel, selectedColor) { isSuccess ->
                        if (isSuccess) {
                            Toast.makeText(context, "Item added!", Toast.LENGTH_LONG).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "Error!", Toast.LENGTH_LONG).show()
                        }
                    }
                },
                enabled = selectedPhone.isNotEmpty() && selectedModel.isNotEmpty() && selectedColor.isNotEmpty()
            ) {
                Text(text = "Submit")
            }
        }
    }
}

@Composable
fun ActionScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TheTopAppBar(
                title = "Report Lost Item",
                navController = navController,
            )
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .padding(innerPadding),
        ) {
            ActionComponent(modifier, navController)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewActionScreen() {
    ActionScreen(
        modifier = Modifier,
        navController = NavHostController(LocalContext.current)
    )
}
