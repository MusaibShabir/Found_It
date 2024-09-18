package com.example.foundit.presentation.screens.input.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foundit.presentation.screens.input.common.components.CategoryCard
import com.example.foundit.presentation.screens.input.data.parentCategories

@Composable
fun ParentCategoryScreen(
    modifier: Modifier
) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .padding(),
        verticalArrangement = Arrangement.Bottom,
        horizontalArrangement = Arrangement.Start,
        columns = GridCells.Adaptive(minSize = 138.dp)
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


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewParentCategoryScreen() {
    ParentCategoryScreen(
        modifier = Modifier,
    )
}

