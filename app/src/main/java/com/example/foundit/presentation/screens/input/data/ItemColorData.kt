package com.example.foundit.presentation.screens.input.data


data class ItemColorData(
    val id: Int,
    val name: String,
    val category: Category
) {
    enum class Category {
        RED,
        YELLOW,
        GREEN,
        BLACK,
        BLUE,
        WHITE,
        ORANGE,
        PINK,
        BROWN,
        PURPLE,
        TRANSPARENT,
    }
}

val colorCategories = listOf(
    ItemColorData(1, "Red", ItemColorData.Category.RED),
    ItemColorData(2, "Yellow", ItemColorData.Category.YELLOW),
    ItemColorData(3, "Green", ItemColorData.Category.GREEN),
    ItemColorData(4, "Black", ItemColorData.Category.BLACK),
    ItemColorData(5, "Blue", ItemColorData.Category.BLUE),
    ItemColorData(6, "White", ItemColorData.Category.WHITE),
    ItemColorData(7, "Orange", ItemColorData.Category.ORANGE),
    ItemColorData(8, "Pink", ItemColorData.Category.PINK),
    ItemColorData(9, "Brown", ItemColorData.Category.BROWN),
    ItemColorData(10, "Purple", ItemColorData.Category.PURPLE),
    ItemColorData(11, "Transparent", ItemColorData.Category.TRANSPARENT),

    )
