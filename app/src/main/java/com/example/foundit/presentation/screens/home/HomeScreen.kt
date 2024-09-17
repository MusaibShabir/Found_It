package com.example.foundit.presentation.screens.home


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.foundit.R
import com.example.foundit.presentation.screens.home.components.AppName
import com.example.foundit.presentation.screens.home.components.Greetings
import com.example.foundit.presentation.screens.home.components.MainCard
import com.example.foundit.presentation.screens.profile.ProfileViewModel
import com.example.foundit.ui.theme.MainGreen
import com.example.foundit.ui.theme.MainRed


// UI-Only Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    greetingPrefix: String,
    profileName: String?,
    navController: NavHostController,
    lostButtonClick: String,
    foundButtonClick: String,

    //temp parameters
//    phone: String,
//    model: String,
//    color: String,
    firestoreItems: List<Map<String, Any>>,
    viewModel: ProfileViewModel
) {
    Scaffold {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppName(modifier = modifier.clickable(onClick = { viewModel.a() }))
            Greetings(
                modifier = modifier,
                greetingPrefix = greetingPrefix,
                profileName = viewModel.name
            )
            HorizontalDivider(
                thickness = 1.dp,
                modifier = modifier
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            )
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(vertical = 20.dp)
                    .verticalScroll(rememberScrollState(), true),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                //Lost Card
                MainCard(
                    modifier = Modifier,
                    cardHeading = R.string.lost_card_heading,
                    cardTitle = R.string.lost_card_sub_title,
                    buttonName = R.string.lost_card_button,
                    cardColor = MainRed,
                    navController = navController,
                    forwardNavigation = lostButtonClick,
                )

                //Found Card
                MainCard(
                    modifier = Modifier,
                    cardHeading = R.string.found_card_heading,
                    cardTitle = R.string.found_card_sub_title,
                    buttonName = R.string.found_card_button,
                    cardColor = MainGreen,
                    navController = navController,
                    forwardNavigation = foundButtonClick
                )

                firestoreItems.forEach { item ->
                    Column(
                        modifier = modifier
                            .fillMaxSize(),
                            //.padding(innerPadding)
                            //.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = modifier
                                .fillMaxWidth()
                                //.padding(16.dp)
//                                .border(
//                                    2.dp,
//                                    MaterialTheme.colorScheme.primary,
//                                    RoundedCornerShape(8.dp)
//                                ),
                            //elevation = 8.dp,
                            //shape = RoundedCornerShape(16.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .background(MaterialTheme.colorScheme.surface),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                item.forEach { (key, value) ->
                                    if (key == "cardId" || key == "cardType") {

                                    }else{
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = key.replaceFirstChar { it.uppercase() }, // Capitalize keys
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 16.sp,
                                            modifier = Modifier.fillMaxWidth(0.4f) // Adjust key width
                                        )
                                        Spacer(modifier = Modifier.width(16.dp))
                                        Text(
                                            text = value.toString(),
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 16.sp,
                                            modifier = Modifier.fillMaxWidth(0.6f) // Adjust value width
                                        )
                                    }
                                    HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
                                }
                                }
                            }
                        }
                    }
                }


                firestoreItems.forEach { item ->
                    Card(
                        modifier = modifier
                            .fillMaxWidth(),
                    ) {
                        Column(
                            modifier = modifier
                                .fillMaxSize()
                                .padding(20.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center
                        ) {
                            /*
                            item.forEach { (key, value) ->
                                Row(
                                    modifier = modifier,
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "$key: $value",
                                    )
                                }
                                Spacer(modifier.height(10.dp))
                            }
                             */
                            ////// OR
                            Row(
                                modifier = modifier,
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Phone: ${item["phone"]}",
                                )
                            }
                            Spacer(modifier.height(10.dp))
                            Row(
                                modifier = modifier,
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "model: ${item["model"]}",
                                )
                            }
                            Spacer(modifier.height(10.dp))
                            Row(
                                modifier = modifier,
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "color: ${item["color"]}",
                                )
                            }
                            Spacer(modifier.height(10.dp))
                        }
                    }
                    Spacer(modifier.height(10.dp))
                }
            }
        }
    }
}

////////////////  indivisual card screen with scaffold
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LostItemScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { /* Handle close button click */ }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            modifier = Modifier.fillMaxSize(0.8f)
                        )
                    }
                },
                //backgroundColor = Color.Transparent,
                //elevation = 0.dp
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(16.dp)
            ) {
                // "Lost Item" Card
                Card(
                    colors = CardDefaults.cardColors(Color.Red),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row (
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(Color.Red, RoundedCornerShape(16.dp))
                            .padding(horizontal = 14.dp, vertical = 8.dp),

                    ){
                        Text(
                            text = "Lost Item",
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.size(14.dp))
                        CircularProgressIndicator(trackColor = Color.White, strokeCap = StrokeCap.Round, modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                    }

                }

                Spacer(modifier = Modifier.height(16.dp))

                // Title and location row
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Wallet", style = MaterialTheme.typography.bodyLarge)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "📍 Srinagar, Jammu & Kashmir",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                        Text(
                            text = "13-May-2024",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Lazy grid for white boxes
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4), // 4 columns
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(8) { // Number of items in the grid
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .padding(2.dp)
                                .background(Color.Green, shape = RoundedCornerShape(10.dp))
                        )
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
                        Text(text = "Item Description", style = MaterialTheme.typography.bodySmall)
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

            // Floating action button for delete
            FloatingActionButton(
                onClick = { /* Handle delete action */ },
                containerColor = Color.Red,
                contentColor = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 70.dp, end = 30.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete"
                )
            }
        }
    }
}


////////////////  indivisual card screen without scaffold
// For LazyVerticalGrid
@Composable
fun CardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        // Top bar with close icon and Lost Item button
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            //.padding(end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { /* Handle close button click */ },
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    modifier = Modifier.fillMaxSize(0.8f)
                )
            }

//                Row(
//                    modifier = Modifier
//                        .background(Color.Red, RoundedCornerShape(16.dp))
//                        .padding(horizontal = 14.dp, vertical = 8.dp),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(
//                        text = "Lost Item",
//                        color = Color.White,
//                        style = MaterialTheme.typography.titleMedium
//                    )
//                    Spacer(modifier = Modifier.size(14.dp))
//                    CircularProgressIndicator(trackColor = Color.White, strokeCap = StrokeCap.Round, modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
//                }
            Card(
                colors = CardDefaults.cardColors(Color.Red),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(end = 8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row (
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(Color.Red, RoundedCornerShape(16.dp))
                        .padding(horizontal = 14.dp, vertical = 8.dp),

                    ){
                    Text(
                        text = "Found Item",
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.size(14.dp))
                    CircularProgressIndicator(trackColor = Color.White, strokeCap = StrokeCap.Round, modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                }

            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column (
            modifier = Modifier.padding(horizontal = 16.dp)
        ){
            // Title and location row
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Wallet", style = MaterialTheme.typography.headlineLarge)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "📍 Srinagar, Jammu & Kashmir",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                    Text(
                        text = "13-May-2024",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Lazy grid for white boxes
            LazyVerticalGrid(
                columns = GridCells.Fixed(4), // 4 columns
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(8) { // Number of items in the grid
                    Card(
                        modifier = Modifier
                            .size(50.dp)
                            .padding(4.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = CardDefaults.cardColors(Color.Green)
                    ){}
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
                    Text(text = "Item Description", style = MaterialTheme.typography.bodySmall)
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

    Box(modifier = Modifier.fillMaxSize()) {

        // Floating action button for delete
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


// ViewModel Composable
@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: ProfileViewModel,
    navController: NavHostController,
    lostButtonClick: String,
    foundButtonClick: String,
) {
    //Greetings
    val profileData by viewModel.profileData.collectAsState()
    val profileName = profileData?.let { "${it.firstName} ${it.lastName}" }
    val greetingPrefix = stringResource(id = R.string.greeting_prefix)

    //temp parameters
    val firestoreItems by viewModel.firestoreItems.collectAsState()
//    val phone_FireStore = viewModel.phone_FireStore
//    val model_FireStore = viewModel.model_FireStore
//    val color_FireStore = viewModel.color_FireStore

    HomeScreenContent(
        modifier = modifier,
        greetingPrefix = greetingPrefix,
        profileName = profileName,
        navController = navController,
        lostButtonClick = lostButtonClick,
        foundButtonClick = foundButtonClick,

        //temp parameters
//        phone = phone_FireStore,
//        model = model_FireStore,
//        color = color_FireStore,
        firestoreItems = firestoreItems,
        viewModel = viewModel
    )
}


/*
@Composable
@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_2")
fun PreviewHomeScreen() {
    HomeScreenContent(
        modifier = Modifier,
        greetingPrefix = "HI",
        profileName = "Musaib Shabir",
        navController = NavHostController(LocalContext.current),
        lostButtonClick = "",
        foundButtonClick = "",

        //temp parameters
        phone = "iPhone 15",
        model = "Black",
        color = "Red"
    )
}

 */

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewLostItemScreen(){
    LostItemScreen(
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewCardScreen(){
    CardScreen()
}

