package com.example.foundit.presentation.screens.progress.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.foundit.presentation.screens.input.common.components.CategoryCard
import com.example.foundit.presentation.screens.input.data.childCategories
import com.example.foundit.presentation.screens.progress.MatchedCardFullScreenViewModel
import com.example.foundit.ui.theme.MainGreen
import com.example.foundit.ui.theme.MainRed
import com.google.firebase.Timestamp

// For LazyVerticalGrid
@Composable
fun MatchedCardFullScreen(
    modifier: Modifier,
    cardId: String,
    navController: NavHostController// Use Hilt to inject ViewModel
) {
    // Collect the card data from the ViewModel
    val viewModel: MatchedCardFullScreenViewModel = hiltViewModel()
    val cardData by viewModel.cardData.collectAsState()

    // Fetch the data when the composable is first launched
    LaunchedEffect(cardId) {
        viewModel.fetchCardData(cardId) // Fetch the card data
    }

    // Display card data
    cardData?.let { data ->

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
            }

            Spacer(modifier = modifier.height(8.dp))


            Column(modifier = modifier.padding(horizontal = 16.dp)) {
                // Title and location row
                Column(modifier = modifier.fillMaxWidth()) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ){
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
                        ){
                            Icon(
                                imageVector = Icons.Rounded.LocationOn,
                                contentDescription = "Close",
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier.width(4.dp))
                            Text(
                                text = truncateText(
                                    text = data["locationAddress"]?.toString() ?: "Unknown location",
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
                            text = formatDate(data["date"] as? Timestamp),
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Lazy grid for white boxes
                LazyVerticalGrid(
                    modifier = modifier
                        .fillMaxWidth(),
                    columns = GridCells.Adaptive(minSize = 120.dp),

                    ) {
                    //val mapData = data["a"] as? Map<String, Any> ?: emptyMap()

                    val input = data["childCategory"].toString()
                    val charList = input.split(",").map { it.trim() }


                    items(childCategories) { category ->
                        CategoryCard(
                            modifier = modifier,
                            categoryText = category.name,
                            borderColor = borderColor,
                        )
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
                    border = BorderStroke(width = 1.dp, color = borderColor)
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    ElevatedButton(
                        onClick = {

                        },
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = MainRed,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth(0.38f)
                            .padding(top = 30.dp)
                            .height(50.dp)
                    ) {
                        Text(
                            text = "Select",
                            fontSize = 18.sp,
                        )
                    }
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = false)
fun PreviewMatchedCardFullScreen() {
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