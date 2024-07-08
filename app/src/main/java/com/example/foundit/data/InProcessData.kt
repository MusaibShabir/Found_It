package com.example.foundit.data

data class InProcessDataItem (
    val cardColorCode: Int,
    val cardTitle: String,
)

val InProcessData = listOf(
    InProcessDataItem(cardColorCode = 0, cardTitle = "Card 1"),
    InProcessDataItem(cardColorCode = 1, cardTitle = "Card 2"),
    InProcessDataItem(cardColorCode = 0, cardTitle = "Card 3"),
    InProcessDataItem(cardColorCode = 1, cardTitle = "Card 4"),
    InProcessDataItem(cardColorCode = 0, cardTitle = "Card 5"),
    InProcessDataItem(cardColorCode = 1, cardTitle = "Card 6"),
    InProcessDataItem(cardColorCode = 0, cardTitle = "Card 7"),
)