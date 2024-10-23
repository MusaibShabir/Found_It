package com.example.foundit.presentation.screens.progress.components

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.foundit.presentation.data.navigation.NavRoutes
import com.example.foundit.presentation.screens.input.common.components.CategoryCard
import com.example.foundit.presentation.screens.input.data.childCategories
import com.example.foundit.presentation.screens.progress.ProgressCardFullScreenViewModel
import com.example.foundit.ui.theme.MainGreen
import com.example.foundit.ui.theme.MainRed
import com.example.foundit.ui.theme.RobotFamily
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
    val matchedCards by viewModel.matchedCards.collectAsState()

    val context = LocalContext.current

    // Fetch the data when the composable is first launched
    LaunchedEffect(cardId) {
        viewModel.fetchCardData(cardId) // Fetch the card data
    }

    // Display card data
    cardData?.let { data ->

        LaunchedEffect(data["matches"]) {
            try {
                val matchedCardId = data["matches"] as? List<String> ?: emptyList()
                viewModel.fetchMatchedCards(matchedCardId)
            } catch (e: Exception) {
                Log.d("profile", "ProgressCardFullScreen: $e")
            }
        }

        val cardColor = when (data["cardType"].toString()) {
            "0" -> MainRed.copy(alpha = .9f)
            "1" -> MainGreen.copy(alpha = .9f)
            else -> Color.Yellow.copy(alpha = .9f)
        }

        val cardLabel = when (if (data["cardType"].toString().length == 1) {
            data["cardType"].toString()
        } else {
            data["cardType"].toString().drop(1)
        }) {
            "0" -> "Lost"
            "1" -> "Found"
            else -> "Halted"
        }

        val borderColor = when (cardLabel) {
            "Lost" -> MainRed.copy(alpha = .5f)
            "Found" -> MainGreen.copy(alpha = .5f)
            else -> Color.Yellow.copy(alpha = .5f)
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
                    elevation = CardDefaults.cardElevation(8.dp),
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
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.titleMedium
                        )
                        if (data["status"].toString() == "0") {
                            Spacer(modifier = Modifier.size(12.dp))

                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.onPrimary,
                                strokeCap = StrokeCap.Round,
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.dp
                            )
                        } else if (data["status"].toString() == "-1") {
                            Spacer(modifier = Modifier.size(14.dp))

                            CircularProgressIndicator(
                                progress = { 0.08f },
                                color = Color.Transparent, // Use color for progress indicator
                                trackColor = Color.Yellow,
                                strokeCap = StrokeCap.Round,
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.dp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = modifier.height(8.dp))


            Column(modifier = modifier.padding(horizontal = 16.dp)) {
                // Title and location row
                Column(modifier = modifier.fillMaxWidth()) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = data["parentCategory"]?.toString() ?: "Unknown",
                            style = MaterialTheme.typography.headlineLarge
                        )

                        Spacer(modifier.width(8.dp))

                        Icon(
                            imageVector = Icons.Default.FiberManualRecord,
                            contentDescription = "Close",
                            modifier = Modifier
                                .size(12.dp)
                        )

                        Spacer(modifier.width(8.dp))

                        Text(
                            text = data["color"]?.toString() ?: "Unknown Colour",
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }

                    HorizontalDivider(modifier.padding(vertical = 8.dp))

                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier
                                .width(IntrinsicSize.Max)
                                .height(IntrinsicSize.Max),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.LocationOn,
                                contentDescription = "Close",
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier.width(4.dp))
                            Text(
                                text = truncateText(
                                    text = data["locationAddress"]?.toString()
                                        ?: "Unknown location",
                                    maxLength = 26
                                ),
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray,
                                overflow = TextOverflow.Ellipsis,
                                softWrap = true,
                                maxLines = 1
                            )
                        }

                        Text(
                            text = data["date"]?.toString() ?: "No date available",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Lazy grid for white boxes
                LazyVerticalGrid(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(120.dp)
                    ,
                    columns = GridCells.Adaptive(minSize = 102.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    val input = data["childCategory"]?.toString()
                    if (!input.isNullOrEmpty()) {
                        val charList = input.split(",")
                            .mapNotNull { it.trim().toIntOrNull() }
                            .filter { id -> childCategories.any { it.id == id } }

                        Log.d("CharList", "Contents of charList: $charList")

                        val filteredCategories = childCategories.filter { category ->
                            charList.contains(category.id)
                        }

                        if (filteredCategories.isNotEmpty()) {
                            items(filteredCategories) { category ->
                                CategoryCard(
                                    modifier = modifier,
                                    categoryText = category.name,
                                    borderColor = borderColor
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = modifier.height(16.dp))


                // Item description card
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 120.dp, max = 240.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    border = BorderStroke(width = 1.dp, color = borderColor),
                    colors = CardDefaults.outlinedCardColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                            .padding(12.dp)
                    ) {
                        Text(
                            text = "Item Description",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                        Text(
                            text = data["cardDescription"]?.toString()
                                ?: "No description available",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
                Spacer(modifier.height(16.dp))

                // Confirm Button for Lost Item Card
                if (cardLabel == "Lost" && data["isMatchEmpty"].toString() == "0" && data["status"].toString() == "0") {
                    Column(
                        modifier
                            .fillMaxSize()
                            .padding(bottom = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Row(
                            modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Did you found your item?",
                                fontSize = 16.sp,
                                fontFamily = RobotFamily,
                                fontWeight = FontWeight.Medium,
                                fontStyle = FontStyle.Italic
                            )

                        }

                        Spacer(modifier.height(16.dp))

                        Row(
                            modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedCard(
                                modifier
                                    .fillMaxWidth()
                                    .height(IntrinsicSize.Max),
                                shape = RoundedCornerShape(38.dp),
                                elevation = CardDefaults.cardElevation(4.dp),
                                colors = CardDefaults.outlinedCardColors(containerColor = MaterialTheme.colorScheme.background)
                            ) {

                                Row(
                                    modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Button(
                                        modifier = modifier.width(112.dp),
                                        onClick = {
                                            viewModel.cardNotMatched(data["matches"].toString(),cardId){isSuccessfull ->
                                                if (isSuccessfull){
                                                    //navController.popBackStack()
                                                } else {
                                                    // Todo: Handle failure
                                                }
                                            }
                                        }
                                    ) {
                                        Text(
                                            text = "No",
                                            fontSize = 16.sp,
                                            fontFamily = RobotFamily,
                                            fontWeight = FontWeight.Medium,
                                        )
                                    }

                                    Button(
                                        modifier = modifier.width(112.dp),
                                        onClick = {
                                            viewModel.cardMatched(data["matches"].toString(),cardId){isSuccessfull ->
                                                if (isSuccessfull){
                                                    //navController.popBackStack()
                                                } else {
                                                    // Todo: Handle failure
                                                }
                                            }
                                        }
                                    ) {
                                        Text(
                                            text = "Yes",
                                            fontSize = 16.sp,
                                            fontFamily = RobotFamily,
                                            fontWeight = FontWeight.Medium,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }


                Spacer(modifier = modifier.height(8.dp))
                HorizontalDivider()
                Spacer(modifier = modifier.height(8.dp))


                LazyColumn(
                    modifier = modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(matchedCards) { item ->
                        MatchedCard(
                            modifier = modifier,
                            cardItem = item,
                            navController = navController
                        )
                    }
                }
            }
        }


        if (data["status"].toString() != "1"){
            // Floating action button for delete
            Box(modifier = modifier.fillMaxSize()) {
                FloatingActionButton(
                    modifier = modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 30.dp, bottom = 70.dp)
                        .size(72.dp),
                    shape = CircleShape,
                    onClick = {
                        if (isNetworkAvailable(context)) {
                            viewModel.deleteCardData(cardId) { isSuccess, e ->
                                if (isSuccess) {
                                    Toast.makeText(
                                        context,
                                        "Card deleted successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
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
                            Toast.makeText(
                                context,
                                "No internet connection. Please try again when connected.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },

                    containerColor = MainRed,
                    contentColor = Color.White
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}

fun truncateText(text: String, maxLength: Int): String {
    return if (text.length > maxLength) {
        text.substring(0, maxLength) + "..."
    } else {
        text
    }
}

@Composable
fun MatchedCard(
    modifier: Modifier,
    cardItem: Map<String, Any>,
    navController: NavHostController
) {
    val context = LocalContext.current

    val cardColor = when (cardItem["cardType"].toString()) {
        "0" -> MainRed.copy(alpha = 0.65f)
        "1" -> MainGreen.copy(alpha = 0.65f)
        else -> Color.Gray.copy(alpha = 0.7f)
    }

    OutlinedCard(
        onClick = {
            try {
                navController.navigate(NavRoutes.MATCHED_CARD_FULL_SCREEN + "/${cardItem["cardId"]}")
            } catch (e: Exception) {
                Toast.makeText(context,"error",Toast.LENGTH_SHORT).show()
            }
        },
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        modifier = modifier
            .fillMaxWidth()
            //.height(160.dp)
            .padding(top = 16.dp, bottom = 0.dp),
    ) {
        Column (
            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ){
            Row (
                modifier = Modifier.fillMaxWidth() ,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ){
                Text(
                    text = "${cardItem["parentCategory"]}",
                    fontWeight = FontWeight.Bold
                )
            }

            Column (
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ){
                Box(
                    modifier = modifier,
                ) {
                    Row(
                        modifier = modifier,
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Location",
                            modifier = modifier.size(14.dp)
                        )
                        Text(
                            text = cardItem["locationAddress"].toString(),
                            //textAlign = TextAlign.Center,
                            fontSize = 12.sp,
                            fontStyle = FontStyle.Italic,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }
            }
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