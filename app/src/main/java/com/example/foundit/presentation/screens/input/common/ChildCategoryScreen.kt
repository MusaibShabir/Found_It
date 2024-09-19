package com.example.foundit.presentation.screens.input.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foundit.presentation.screens.input.common.components.CategoryCard
import com.example.foundit.presentation.screens.input.data.childCategories
import com.example.foundit.presentation.screens.input.lost.LostInputViewModel


@Composable
fun ChildCategoryScreen(
    modifier: Modifier,
    viewModel: LostInputViewModel
) {

    val selectedCategoryIds by viewModel.selectedChildCategoryIds.collectAsState()

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .padding(),
        verticalArrangement = Arrangement.Bottom,
        columns = GridCells.Adaptive(minSize = 110.dp)
    ) {

        items(childCategories) { childCategory ->
            CategoryCard(
                modifier = Modifier,
                categoryText = childCategory.name,
                isSelected = childCategory.id in selectedCategoryIds,
                onCategoryClick = { viewModel.toggleChildCategorySelection(childCategory.id) },
                multipleSelection = true
            )
        }


    }
}



/*
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewChildCategoryScreen() {
    ChildCategoryScreen(
        modifier = Modifier,
    )
}

 */

