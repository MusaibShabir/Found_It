package com.example.foundit.presentation.screens.input.common

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ParentCategoryScreen(modifier : Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {

    }
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

