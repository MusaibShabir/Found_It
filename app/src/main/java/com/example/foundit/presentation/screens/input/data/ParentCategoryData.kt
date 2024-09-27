package com.example.foundit.presentation.screens.input.data



data class ParentCategoryData(
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
        LUGGAGE,
        OTHER,
    }
}

val parentCategories = listOf(
    ParentCategoryData(1, "Wallet", ParentCategoryData.Category.WALLET),
    ParentCategoryData(2, "Key", ParentCategoryData.Category.KEY),
    ParentCategoryData(3, "Phone", ParentCategoryData.Category.PHONE),
    ParentCategoryData(4, "Glasses", ParentCategoryData.Category.GLASSES),
    ParentCategoryData(5, "Jewellery", ParentCategoryData.Category.JEWELERY),
    ParentCategoryData(6, "Laptop", ParentCategoryData.Category.LAPTOP),
    ParentCategoryData(7, "Headphones", ParentCategoryData.Category.HEADPHONES),
    ParentCategoryData(8, "Backpack", ParentCategoryData.Category.BACKPACKS),
    ParentCategoryData(9, "Umbrella", ParentCategoryData.Category.UMBRELLAS),
    ParentCategoryData(10, "Luggage", ParentCategoryData.Category.LUGGAGE),
    ParentCategoryData(11, "Other", ParentCategoryData.Category.OTHER),
)

