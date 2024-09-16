package com.example.foundit.presentation.screens.input.lost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.foundit.presentation.screens.input.common.CategoryCard

@Composable
fun ParentCategoryScreen() {
    CategoryCard(
        modifier = Modifier,
        categoryText = "Laptop"
    )
}

@Preview(showBackground = true, showSystemUi = false, device = "id:pixel_2")
@Composable
fun PreviewParentCategoryScreen() {
    ParentCategoryScreen()
}

