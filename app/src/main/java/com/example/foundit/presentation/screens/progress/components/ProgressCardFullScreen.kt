package com.example.foundit.presentation.screens.progress.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.foundit.presentation.screens.progress.ProgressFullCardViewModel

// For LazyVerticalGrid
@Composable
fun ProgressCardFullScreen(
    modifier: Modifier,
    cardId: String,
    navController: NavHostController// Use Hilt to inject ViewModel
) {
    // Collect the card data from the ViewModel
    val viewModel: ProgressFullCardViewModel = hiltViewModel()
    val cardData by viewModel.cardData.collectAsState()

    // Fetch the data when the composable is first launched
    LaunchedEffect(cardId) {
        viewModel.fetchCardData(cardId) // Fetch the card data
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        // Top bar with close icon and Lost Item button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    modifier = Modifier.fillMaxSize(0.8f)
                )
            }

            Card(
                colors = CardDefaults.cardColors(Color.Red),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.padding(end = 8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(Color.Red, RoundedCornerShape(16.dp))
                        .padding(horizontal = 14.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Found Item",
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.size(14.dp))
                    CircularProgressIndicator(
                        trackColor = Color.White,
                        strokeCap = StrokeCap.Round,
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Display card data
        cardData?.let { data ->
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                // Title and location row
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(text = data["phone"]?.toString() ?: "Unknown", style = MaterialTheme.typography.headlineLarge)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "📍 ${data["location"]?.toString() ?: "Unknown location"}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                        Text(
                            text = data["date"]?.toString() ?: "Unknown date",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Lazy grid for white boxes
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    val mapData = data["a"] as? Map<String, Any> ?: emptyMap()

                    // Iterate through the map's entries and display the values
                    items(mapData.entries.toList()) { entry ->
                        Card(
                            modifier = Modifier
                                .size(50.dp)
                                .padding(4.dp),
                            elevation = CardDefaults.cardElevation(4.dp),
                            colors = CardDefaults.cardColors(Color.Green)
                        ) {
                            // Display the value of each entry in the grid
                            Text(
                                text = entry.value.toString(),
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Item description card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        Text(text = data["description"]?.toString() ?: "No description available", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }

    // Floating action button for delete
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(
            onClick = { /* Handle delete action */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 30.dp, bottom = 70.dp),
            containerColor = Color.Red,
            contentColor = Color.White
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete"
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = false)
fun PreviewProgressCardFullScreen() {
    val cardItem: Map<String, Any> = mapOf(
        "cardId" to 1,
        "cardType" to 0,
        "date" to "13-May-2024",
        "category" to "Phone",
        "location" to "Srinagar, Jammu & Kashmir",
        "status" to 0
    )
    ProgressCardFullScreen(modifier = Modifier, cardId = cardItem["cardId"].toString(), navController = NavHostController(LocalContext.current))
}