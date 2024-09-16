package com.example.foundit.presentation.screens.input.data



data class ParentCategory(
    val id: Int,
    val name: String,
    val category: Category

) {
    enum class Category {
        WALLET,
        KEY,
        PHONE,
        GLASSES,
        JEWELERY,
        LAPTOP,
        HEADPHONES,
        BACKPACKS,
        UMBRELLAS,
        LUGGAGE
    }
}

val parentCategories = listOf(
    ParentCategory(1, "Wallet", ParentCategory.Category.WALLET),
    ParentCategory(2, "Key", ParentCategory.Category.KEY),
    ParentCategory(1, "Wallet", ParentCategory.Category.PHONE),
    ParentCategory(1, "Wallet", ParentCategory.Category.GLASSES),
    ParentCategory(1, "Wallet", ParentCategory.Category.JEWELERY),
    ParentCategory(1, "Wallet", ParentCategory.Category.LAPTOP),
    ParentCategory(1, "Wallet", ParentCategory.Category.HEADPHONES),
    ParentCategory(1, "Wallet", ParentCategory.Category.BACKPACKS),
    ParentCategory(1, "Wallet", ParentCategory.Category.UMBRELLAS),
    ParentCategory(1, "Wallet", ParentCategory.Category.LUGGAGE)



)

