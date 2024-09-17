package com.example.foundit.presentation.screens.input.data


data class ChildCategoryData(
    val id: Int,
    val name: String,
    val category: Category
) {
    enum class Category {
        SHORT,
        LONG,
        SMALL,
        BIG,
        RED,
        YELLOW,
        GREEN,
        CARDS,
        CASH,

    }
}


val childCategories = listOf(
    ChildCategoryData(1, "Short", ChildCategoryData.Category.SHORT),
    ChildCategoryData(2, "Long", ChildCategoryData.Category.LONG),
    ChildCategoryData(3, "Red", ChildCategoryData.Category.RED),
    ChildCategoryData(4, "Green", ChildCategoryData.Category.GREEN),
    ChildCategoryData(5, "Yellow", ChildCategoryData.Category.YELLOW),
    ChildCategoryData(6, "Cash", ChildCategoryData.Category.CASH),
    ChildCategoryData(7, "Small", ChildCategoryData.Category.SMALL),
    ChildCategoryData(8, "Big", ChildCategoryData.Category.BIG),
    ChildCategoryData(9, "Cards", ChildCategoryData.Category.CARDS),
)

