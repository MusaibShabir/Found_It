package com.example.foundit.presentation.screens.progress.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.foundit.presentation.data.navigation.NavRoutes
import com.example.foundit.ui.theme.MainGreen
import com.example.foundit.ui.theme.MainRed
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import com.google.firebase.Timestamp


//sealed class ProcessCardItem {
//    data class InProcess(val data: Map<String, Any>) : ProcessCardItem()
//    data class Finished(val data: Map<String, Any>) : ProcessCardItem()
//}


@Composable
fun ProcessCard(
    modifier: Modifier,
    cardItem: Map<String, Any>,
    navController: NavHostController
) {

    val context = LocalContext.current

//    val colorCode: Int
//    val itemTitle: String
//    val itemDescription: String
//    val itemLocation: String
//    val progressIndicator: Boolean

//    when (cardItem) {
//        is ProcessCardItem.InProcess -> {
//            colorCode = cardItem.data.cardColorCode
//            itemTitle = cardItem.data.itemTitle
//            itemDescription = cardItem.data.itemDescription
//            itemLocation = cardItem.data.itemLocation
//            progressIndicator = cardItem.data.progressIndicator
//        }
//        is ProcessCardItem.Finished -> {
//            colorCode = cardItem.data.cardColorCode
//            itemTitle = cardItem.data.itemTitle
//            itemDescription = cardItem.data.itemDescription
//            itemLocation = cardItem.data.itemLocation
//            progressIndicator = cardItem.data.progressIndicator
//        }
//    }

//    when (cardItem) {
//        is ProcessCardItem.InProcess -> {
//            colorCode = cardItem.data["status"] as Int
//            itemTitle = cardItem.data["phone"] as String
//            itemDescription = cardItem.data["model"] as String
//            //itemLocation = cardItem.data["itemLocation"] as String
//            itemLocation = "sdfsdf"
//            progressIndicator = true
//        }
//        is ProcessCardItem.Finished -> {
//            colorCode = cardItem.data["status"] as Int
//            itemTitle = cardItem.data["phone"] as String
//            itemDescription = cardItem.data["model"] as String
//            //itemLocation = cardItem.data["itemLocation"] as String
//            itemLocation = "sdfsdf"
//            progressIndicator = false
//        }
//    }

    val cardColor = when (cardItem["cardType"].toString()) {
        "0" -> MainRed.copy(alpha = 0.4f)
        "1" -> MainGreen.copy(alpha = 0.4f)
        else -> Color.Gray.copy(alpha = 0.4f)
    }

    Card(
        onClick = {
            try {
                navController.navigate(NavRoutes.PROGRESS_CARD_FULL_SCREEN + "/${cardItem["cardId"]}")
            } catch (e: Exception) {
                Toast.makeText(context,"error",Toast.LENGTH_SHORT).show()
            }
        },
        colors = CardDefaults.cardColors(containerColor = cardColor),
        modifier = modifier
            .fillMaxWidth()
            //.height(160.dp)
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 0.dp),
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

                if (cardItem["status"].toString() == "0"){
                    CardLinearProgressIndicator()
                }

            }

            Column (
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ){
                Box(
                    modifier = modifier,
                    //contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = modifier,
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Outlined.LocationOn,
                            contentDescription = "Location",
                            modifier = modifier.size(12.dp)
                        )
                        Text(
                            text = formatDate(cardItem["date"] as? Timestamp),
                            //textAlign = TextAlign.Center,
                            fontSize = 12.sp,
                            fontStyle = FontStyle.Italic,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }

                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = "${cardItem["cardDescription"]}",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Start,
                    maxLines = 2
                )
            }
        }
    }
}

@Composable
fun CardLinearProgressIndicator() {

    var progress by remember { mutableFloatStateOf(0f) }
    val coroutineScope = rememberCoroutineScope()


    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch {
            var isIncreasing = true
            while (true) {
                if (isIncreasing) {
                    while (progress < 1f) {
                        progress += 0.02f
                        delay(1)
                    }
                    isIncreasing = false
                } else {
                    while (progress > 0f) {
                        progress -= 0.02f
                        delay(1)
                    }
                    isIncreasing = true
                }
            }
        }
    }

    LinearProgressIndicator(
        progress = { progress },
        modifier = Modifier
            .width(18.dp)
            .height(2.dp),
        color = Color.Blue,
        trackColor = Color.White,
        strokeCap = StrokeCap.Round
    )

}




@Composable
fun ProcessCardList(
    modifier: Modifier,
    cardData: List<Map<String, Any>>,
    navController: NavHostController
) {
    if (cardData.isEmpty()) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Nothing to show here"

            )
        }
    }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        //verticalArrangement = Arrangement.spacedBy(16.dp) // Add space between items if needed
    ) {
        items(cardData) { item ->
            ProcessCard(modifier = modifier, cardItem = item, navController = navController)
        }
    }
}

// date Formater code
fun formatDate(timestamp: Timestamp?): String {
    return if (timestamp != null) {
        // Convert Firestore Timestamp to Date
        val date = timestamp.toDate()

        // Format using DateTimeFormatter
        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
            .withZone(ZoneId.systemDefault()) // Set the time zone (e.g., system default or specific zone)

        val instant = Instant.ofEpochMilli(date.time) // Convert date to Instant
        formatter.format(instant) // Format the instant to a string
    } else {
        "No date available" // Fallback in case of a null timestamp
    }
}

//
//@Composable
//fun FinishedProcessCardList(
//    modifier: Modifier,
//    cardData: List<Map<String, Any>>
//) {
////    val rememberedCardData = remember(cardData) { cardData }
////    LazyColumn(
////        modifier = modifier.fillMaxSize(),
////        verticalArrangement = Arrangement.spacedBy(16.dp)
////    ) {
////        items(rememberedCardData) { cardItem ->
////            ProcessCard(modifier = modifier, cardItem = cardItem)
////        }
////    }
//    Column(
//        modifier = modifier.fillMaxSize()
//    ) {
//        cardData.forEach {item ->
//            ProcessCard(modifier = modifier, cardItem = item)
//        }
//    }
//}
//
//@Composable
//fun HaltedProcessCardList(
//    modifier: Modifier,
//    cardData: List<Map<String, Any>>
//    ) {
////    Column(
////        modifier = modifier
////            .fillMaxSize(),
////        horizontalAlignment = Alignment.CenterHorizontally,
////        verticalArrangement = Arrangement.Center
////    ) {
////        Text(
////            text = "Nothing to show here"
////        )
////    }
//    Column (
//        modifier = modifier.fillMaxSize()
//    ){
//        cardData.forEach {item ->
//            ProcessCard(modifier = modifier, cardItem = item)
//        }
//    }
//}

/*
@Composable
@Preview(showBackground = true, showSystemUi = false, device = "id:pixel_6_pro")
fun PreviewInProcessCard() {
    ProcessCard(
        modifier = Modifier,
        cardItem = ProcessCardItem.InProcess(
            data = InProcessDataItem(
                itemTitle = "Watch",
                cardColorCode = 1,
                itemDescription = "This is the description of the card, which will specify the item and its details.",
                itemLocation = "Srinagar Jammu & Kashmir",
                progressIndicator = true
            )
        )
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = false, device = "id:pixel_6_pro")
fun PreviewFinishedProcessCard() {
    ProcessCard(
        modifier = Modifier,
        cardItem = ProcessCardItem.Finished(
            data = FinishedProcessDataItem(
                itemTitle = "Watch",
                cardColorCode = 1,
                itemDescription = "This is the description of the card, which will specify the item and its details.",
                itemLocation = "Srinagar Jammu & Kashmir",
                progressIndicator = false
            )
        )
    )
}



*/

