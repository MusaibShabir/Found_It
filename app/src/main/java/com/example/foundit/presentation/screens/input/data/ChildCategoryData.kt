package com.example.foundit.presentation.screens.input.data


data class ChildCategoryData(
    val id: Int,
    val parentCategoryId: Int,
    val name: String,
    val category: Category
) {
    enum class Category {
        SHORT,
        LONG,
        SMALL,
        BIG,
        CARDS,
        CASH,
        LEATHER,
        FABRIC,
        NYLON,
        METAL,
        PENNIES,
        VEHICLE_KEY,
        NORMAL_KEY,
        SINGLE_KEY,
        MULTIPLE_KEYS,
        TOUCH,
        NON_TOUCH,
        ROUND,
        SQUARE,
        PLASTIC,
        RING,
        CHAIN,
        EARRING,
        BRACELET,
        GOLD,
        SILVER,
        DIAMOND,
        PLATINUM,
        FOLDABLE,
        HEADPHONE,
        EARPHONE,
        WIRELESS,
        WIRED,
    }
}


val childCategories = listOf(
    ChildCategoryData(1, 1,"Short", ChildCategoryData.Category.SHORT),
    ChildCategoryData(7, 1,"Small", ChildCategoryData.Category.SMALL),
    ChildCategoryData(8, 1,"Big", ChildCategoryData.Category.BIG),
    ChildCategoryData(9, 1,"Cards", ChildCategoryData.Category.CARDS),


    // For Wallet Category
    ChildCategoryData(10, 1,"Leather", ChildCategoryData.Category.LEATHER),
    ChildCategoryData(11, 1,"Fabric", ChildCategoryData.Category.FABRIC),
    ChildCategoryData(12, 1,"Metal", ChildCategoryData.Category.METAL),
    ChildCategoryData(13, 1,"Cash", ChildCategoryData.Category.CASH),
    ChildCategoryData(14, 1,"Pennies", ChildCategoryData.Category.PENNIES),
    ChildCategoryData(15, 1,"Cards", ChildCategoryData.Category.CARDS),



    // For Key Category
    ChildCategoryData(16, 2,"Short", ChildCategoryData.Category.SHORT),
    ChildCategoryData(17, 2,"Long", ChildCategoryData.Category.LONG),
    ChildCategoryData(18, 2,"Normal Key", ChildCategoryData.Category.NORMAL_KEY),
    ChildCategoryData(19, 2,"Vehicle Key", ChildCategoryData.Category.VEHICLE_KEY),
    ChildCategoryData(20, 2,"Single Key", ChildCategoryData.Category.SINGLE_KEY),
    ChildCategoryData(21, 2,"Multiple Keys", ChildCategoryData.Category.MULTIPLE_KEYS),


    // For Phone Category
    ChildCategoryData(22, 3,"Touch", ChildCategoryData.Category.TOUCH),
    ChildCategoryData(23, 3,"Non-Touch", ChildCategoryData.Category.NON_TOUCH),

    // For Glasses Category
    ChildCategoryData(24, 4,"Round", ChildCategoryData.Category.ROUND),
    ChildCategoryData(25, 4,"Square", ChildCategoryData.Category.SQUARE),
    ChildCategoryData(26, 4,"Plastic", ChildCategoryData.Category.PLASTIC),
    ChildCategoryData(27, 4,"Plastic", ChildCategoryData.Category.METAL),

    // For Jewellery Category
    ChildCategoryData(28, 5,"Ring", ChildCategoryData.Category.RING),
    ChildCategoryData(29, 5,"Chain", ChildCategoryData.Category.CHAIN),
    ChildCategoryData(30, 5,"Earrings", ChildCategoryData.Category.EARRING),
    ChildCategoryData(31, 5,"Bracelet", ChildCategoryData.Category.BRACELET),
    ChildCategoryData(32, 5,"Gold", ChildCategoryData.Category.GOLD),
    ChildCategoryData(33, 5,"Silver", ChildCategoryData.Category.SILVER),
    ChildCategoryData(34, 5,"Diamond", ChildCategoryData.Category.DIAMOND),
    ChildCategoryData(35, 5,"Platinum", ChildCategoryData.Category.PLATINUM),


    // For Laptop Category
    ChildCategoryData(36, 6,"Foldable", ChildCategoryData.Category.FOLDABLE),


    // For Headphones Category
    ChildCategoryData(37, 7,"Headphone", ChildCategoryData.Category.HEADPHONE),
    ChildCategoryData(38, 7,"Earphone", ChildCategoryData.Category.EARPHONE),
    ChildCategoryData(39, 7,"Wireless", ChildCategoryData.Category.WIRELESS),
    ChildCategoryData(40, 7,"Wired", ChildCategoryData.Category.WIRED),


    // For Backpack Category
    ChildCategoryData(41, 8,"Leather", ChildCategoryData.Category.LEATHER),
    ChildCategoryData(42, 8,"Fabric", ChildCategoryData.Category.FABRIC),
    ChildCategoryData(43, 8,"Nylon", ChildCategoryData.Category.NYLON),
    ChildCategoryData(44, 8,"Cash", ChildCategoryData.Category.CASH),
    ChildCategoryData(45, 8,"Pennies", ChildCategoryData.Category.PENNIES),
    ChildCategoryData(46, 8,"Cards", ChildCategoryData.Category.CARDS),














    )

