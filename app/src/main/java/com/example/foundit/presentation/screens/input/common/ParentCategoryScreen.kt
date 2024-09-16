package com.example.foundit.presentation.screens.input.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foundit.presentation.screens.input.data.parentCategories

@Composable
fun ParentCategoryScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        LazyVerticalGrid(
            modifier = modifier.fillMaxSize(),
            columns = GridCells.Adaptive(90.dp)
        ) {
            items(parentCategories) { parentCategory ->
                CategoryCard(
                    modifier = Modifier,
                    categoryText = parentCategory.name,
                    onCategoryClick = { }
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_2")
@Composable
fun PreviewParentCategoryScreen() {
    ParentCategoryScreen()
}

