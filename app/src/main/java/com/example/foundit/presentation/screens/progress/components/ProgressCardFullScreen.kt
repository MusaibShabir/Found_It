package com.example.foundit.presentation.screens.progress.components

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.foundit.presentation.screens.progress.ProgressCardFullScreenViewModel
import com.example.foundit.ui.theme.MainGreen
import com.example.foundit.ui.theme.MainRed
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestoreException


// to check network connection for deleting the card
fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork
    val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
    return networkCapabilities != null &&
            (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
}

// For LazyVerticalGrid
@Composable
fun ProgressCardFullScreen(
    modifier: Modifier,
    cardId: String,
    navController: NavHostController// Use Hilt to inject ViewModel
) {
    // Collect the card data from the ViewModel
    val viewModel: ProgressCardFullScreenViewModel = hiltViewModel()
    val cardData by viewModel.cardData.collectAsState()

    val context = LocalContext.current

    // Fetch the data when the composable is first launched
    LaunchedEffect(cardId) {
        viewModel.fetchCardData(cardId) // Fetch the card data
    }

    // Display card data
    cardData?.let { data ->
        val cardColor = when (data["cardType"].toString()) {
            "0" -> MainRed.copy(alpha = 0.4f)
            "1" -> MainGreen.copy(alpha = 0.4f)
            else -> Color.Gray.copy(alpha = 0.4f)
        }

        val cardLabel = when (data["cardType"].toString().drop(0)) {
            "0" -> "Lost"
            "1" -> "Found"
            else -> "Halted"
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 16.dp, start = 2.dp, end = 4.dp)
        ) {
            // Top bar with close icon and Lost Item button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        modifier = Modifier.fillMaxSize(0.8f)
                    )
                }

                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.padding(end = 8.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(cardColor)
                            .padding(horizontal = 14.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = cardLabel,
                            color = Color.White, // Text color remains white
                            style = MaterialTheme.typography.titleMedium
                        )
                        if (data["status"].toString() == "0") {
                            Spacer(modifier = Modifier.size(14.dp))

                            CircularProgressIndicator(
                                color = Color.White, // Use color for progress indicator
                                strokeCap = StrokeCap.Round,
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.dp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))


            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                // Title and location row
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = data["parentCategory"]?.toString() ?: "Unknown",
                            style = MaterialTheme.typography.headlineLarge
                        )
                        VerticalDivider(
                            thickness = 2.dp,
                            modifier = Modifier
                                .height(30.dp)
                                .padding(horizontal = 12.dp),
                            color = Color.Black
                        )
                        Text(
                            text = data["color"]?.toString() ?: "Unknown Colour",
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }

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
                            text = formatDate(data["date"] as? Timestamp),
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
                        //.height(150.dp),
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    //val mapData = data["a"] as? Map<String, Any> ?: emptyMap()

                    val input = data["childCategory"].toString()
                    val charList = input.split(",").map { it.trim() }

                    // Iterate through the map's entries and display the values
                    items(charList) { entry ->
                        Card(
                            modifier = Modifier
                                .size(50.dp)
                                .padding(4.dp),
                            elevation = CardDefaults.cardElevation(4.dp),
                            //colors = CardDefaults.cardColors(Color.Green)
                        ) {
                            // Display the value of each entry in the grid
                            Text(
                                text = entry,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Item description card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                            .padding(12.dp)
                    ) {
                        Text(text = "Item Description", style = MaterialTheme.typography.bodyLarge)
                        HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp), thickness = 2.dp)
                        Text(
                            text = data["cardDescription"]?.toString()
                                ?: "No description available",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Area for matched cards
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Area for Matched Cards", color = Color.DarkGray)
                }
            }
        }
    }

    // Floating action button for delete
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(
            onClick = {
                if (isNetworkAvailable(context)) {
                    viewModel.deleteCardData(cardId) { isSuccess, e ->
                        if (isSuccess) {
                            Toast.makeText(context, "Card deleted successfully", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            val errorMessage = when (e) {
                                is FirebaseFirestoreException -> {
                                    when (e.code) {
                                        FirebaseFirestoreException.Code.PERMISSION_DENIED -> "You don't have permission to delete this card."
                                        FirebaseFirestoreException.Code.NOT_FOUND -> "Card not found, unable to delete."
                                        FirebaseFirestoreException.Code.UNAVAILABLE -> "Firestore service is currently unavailable. Please try again later."
                                        FirebaseFirestoreException.Code.CANCELLED -> "Deletion was cancelled."
                                        FirebaseFirestoreException.Code.ABORTED -> "Operation aborted. Please try again."
                                        else -> "An unexpected error occurred while deleting the card."
                                    }
                                }
                                is IllegalArgumentException -> "Invalid input provided."
                                is NullPointerException -> "An unexpected error occurred."
                                else -> "An unknown error occurred: ${e?.message}"
                            }
                            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(context, "No internet connection. Please try again when connected.", Toast.LENGTH_SHORT).show()
                }
            },
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
    ProgressCardFullScreen(
        modifier = Modifier,
        cardId = cardItem["cardId"].toString(),
        navController = NavHostController(LocalContext.current)
    )
}