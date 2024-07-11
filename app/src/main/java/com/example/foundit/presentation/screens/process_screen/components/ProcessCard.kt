package com.example.foundit.presentation.screens.process_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.foundit.data.FinishedProcessDataItem
import com.example.foundit.data.InProcessDataItem
import com.example.foundit.ui.theme.MainGreen
import com.example.foundit.ui.theme.MainRed


sealed class ProcessCardItem {
    data class InProcess(val data: InProcessDataItem) : ProcessCardItem()
    data class Finished(val data: FinishedProcessDataItem) : ProcessCardItem()
}

@Composable
fun ProcessCard(
    modifier: Modifier,
    cardItem: ProcessCardItem
) {

    val (colorCode, cardTitle) = when (cardItem) {
        is ProcessCardItem.InProcess -> cardItem.data.cardColorCode to cardItem.data.cardTitle
        is ProcessCardItem.Finished -> cardItem.data.cardColorCode to cardItem.data.cardTitle
        // Both of them are Returning a Pair Object which is then passed to the Val
        //"to" -> helping with creating a pair object
    }

    val cardColor = when (colorCode) {
        0 -> MainRed.copy(alpha = 0.2f)
        1 -> MainGreen.copy(alpha = 0.2f)
        else -> Color.Gray.copy(alpha = 0.2f)
    }

    Card(
        onClick = { /*TODO*/ },
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = cardTitle)
        }
    }
}

@Composable
fun InProcessCardList(
    modifier: Modifier,
    cardData: List<ProcessCardItem.InProcess>
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(cardData) { cardItem ->
            ProcessCard(modifier = modifier, cardItem = cardItem)
        }
    }
}

@Composable
fun FinishedProcessCardList(
    modifier: Modifier,
    cardData: List<ProcessCardItem.Finished>
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(cardData) { cardItem ->
            ProcessCard(modifier = modifier, cardItem = cardItem)
        }
    }
}