package com.example.foundit.presentation.data

data class InProcessDataItem (
    val cardColorCode: Int,
    val itemTitle: String,
    val itemDescription: String,
    val itemLocation: String,
    val progressIndicator: Boolean
)

var InProcessData = listOf(
    InProcessDataItem(
        cardColorCode = 0,
        itemTitle = "Card 1",
        itemDescription = "This is the description of the card, which will specify the item and its details.",
        itemLocation = "Srinagar Jammu & Kashmir",
        progressIndicator = true
    ),

    InProcessDataItem(
        cardColorCode = 1,
        itemTitle = "Card 2",
        itemDescription = "This is the description of the card, which will specify the item and its details.",
        itemLocation = "Srinagar Jammu & Kashmir",
        progressIndicator = true
    ),

    InProcessDataItem(
        cardColorCode = 0,
        itemTitle = "Card 3",
        itemDescription = "This is the description of the card, which will specify the item and its details.",
        itemLocation = "Srinagar Jammu & Kashmir",
        progressIndicator = true
    ),

    InProcessDataItem(
        cardColorCode = 1,
        itemTitle = "Card 4",
        itemDescription = "This is the description of the card, which will specify the item and its details.",
        itemLocation = "Srinagar Jammu & Kashmir",
        progressIndicator = true
    ),

    InProcessDataItem(
        cardColorCode = 0,
        itemTitle = "Card 5",
        itemDescription = "This is the description of the card, which will specify the item and its details.",
        itemLocation = "Srinagar Jammu & Kashmir",
        progressIndicator = true
    ),

    InProcessDataItem(
        cardColorCode = 1,
        itemTitle = "Card 6",
        itemDescription = "This is the description of the card, which will specify the item and its details.",
        itemLocation = "Srinagar Jammu & Kashmir",
        progressIndicator = true
    ),

    InProcessDataItem(
        cardColorCode = 0,
        itemTitle = "Card 7",
        itemDescription = "This is the description of the card, which will specify the item and its details.",
        itemLocation = "Srinagar Jammu & Kashmir",
        progressIndicator = true
    ),
)

// Function to be used for adding a new card to the list
fun addCard(
    cardColorCode: Int,
    itemTitle: String,
    itemDescription: String,
    itemLocation: String,
    progressIndicator: Boolean
) {
    val newCard = InProcessDataItem(
        cardColorCode,
        itemTitle,
        itemDescription,
        itemLocation,
        progressIndicator
    )
    InProcessData = InProcessData + newCard
}
