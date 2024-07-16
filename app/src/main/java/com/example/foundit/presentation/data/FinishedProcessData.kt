package com.example.foundit.presentation.data

data class FinishedProcessDataItem (
    val cardColorCode: Int,
    val itemTitle: String,
    val itemDescription: String,
    val itemLocation: String,
    val progressIndicator: Boolean
)
val FinishedProcessData = listOf(
    FinishedProcessDataItem(
        cardColorCode = 1,
        itemTitle = "Card 2",
        itemDescription = "This is the description of the card, which will specify the item and its details.",
        itemLocation = "Srinagar Jammu & Kashmir",
        progressIndicator = false
    ),

    FinishedProcessDataItem(
        cardColorCode = 0,
        itemTitle = "Ring",
        itemDescription = "This is the description of the card, which will specify the item and its details.",
        itemLocation = "Baramulla Jammu & Kashmir",
        progressIndicator = false
    ),

    )