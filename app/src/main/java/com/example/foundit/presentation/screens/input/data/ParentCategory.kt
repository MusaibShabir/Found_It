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
    ParentCategory(3, "Phone", ParentCategory.Category.PHONE),
    ParentCategory(4, "Glasses", ParentCategory.Category.GLASSES),
    ParentCategory(5, "Jawelery", ParentCategory.Category.JEWELERY),
    ParentCategory(6, "Laptop", ParentCategory.Category.LAPTOP),
    ParentCategory(7, "Headphones", ParentCategory.Category.HEADPHONES),
    ParentCategory(8, "Backpack", ParentCategory.Category.BACKPACKS),
    ParentCategory(9, "Umbrella", ParentCategory.Category.UMBRELLAS),
    ParentCategory(10, "Luggage", ParentCategory.Category.LUGGAGE)



)

