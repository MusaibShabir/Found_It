package com.example.foundit.presentation.screens.progress.components

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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foundit.presentation.data.FinishedProcessDataItem
import com.example.foundit.presentation.data.InProcessDataItem
import com.example.foundit.ui.theme.MainGreen
import com.example.foundit.ui.theme.MainRed
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


sealed class ProcessCardItem {
    data class InProcess(val data: InProcessDataItem) : ProcessCardItem()
    data class Finished(val data: FinishedProcessDataItem) : ProcessCardItem()
}


@Composable
fun ProcessCard(
    modifier: Modifier,
    cardItem: ProcessCardItem
) {

    val colorCode: Int
    val itemTitle: String
    val itemDescription: String
    val itemLocation: String
    val progressIndicator: Boolean

    when (cardItem) {
        is ProcessCardItem.InProcess -> {
            colorCode = cardItem.data.cardColorCode
            itemTitle = cardItem.data.itemTitle
            itemDescription = cardItem.data.itemDescription
            itemLocation = cardItem.data.itemLocation
            progressIndicator = cardItem.data.progressIndicator
        }
        is ProcessCardItem.Finished -> {
            colorCode = cardItem.data.cardColorCode
            itemTitle = cardItem.data.itemTitle
            itemDescription = cardItem.data.itemDescription
            itemLocation = cardItem.data.itemLocation
            progressIndicator = cardItem.data.progressIndicator
        }
    }


    val cardColor = when (colorCode) {
        0 -> MainRed.copy(alpha = 0.4f)
        1 -> MainGreen.copy(alpha = 0.4f)
        else -> Color.Gray.copy(alpha = 0.4f)
    }

    Card(
        onClick = { /*TODO*/ },
        colors = CardDefaults.cardColors(containerColor = cardColor),
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 0.dp),
    ) {
        Column (
            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row (
                modifier = Modifier.fillMaxWidth() ,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ){
                Text(
                    text = itemTitle,
                    fontWeight = FontWeight.Bold
                )

                if (progressIndicator){
                    CardLinearProgressIndicator()
                }

            }

            Column (
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ){
                Box(
                    modifier = modifier,
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = modifier,
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Outlined.LocationOn,
                            contentDescription = "Location",
                            modifier = modifier.size(12.dp)
                        )
                        Text(
                            text = itemLocation,
                            //textAlign = TextAlign.Center,
                            fontSize = 12.sp,
                            fontStyle = FontStyle.Italic,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }

                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = itemDescription,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Start,
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
fun InProcessCardList(
    modifier: Modifier,
    cardData: List<ProcessCardItem.InProcess>
) {
    val rememberedCardData = remember(cardData) { cardData }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(rememberedCardData) { cardItem ->
            ProcessCard(modifier = modifier, cardItem = cardItem)
        }
    }
}


@Composable
fun FinishedProcessCardList(
    modifier: Modifier,
    cardData: List<ProcessCardItem.Finished>
) {
    val rememberedCardData = remember(cardData) { cardData }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(rememberedCardData) { cardItem ->
            ProcessCard(modifier = modifier, cardItem = cardItem)
        }
    }
}

@Composable
fun HaltedProcessCardList(
    modifier: Modifier,
    ) {
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



