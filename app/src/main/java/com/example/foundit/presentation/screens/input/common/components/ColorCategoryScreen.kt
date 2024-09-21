package com.example.foundit.presentation.screens.input.common.components

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
import com.example.foundit.presentation.screens.input.data.colorCategories
import com.example.foundit.presentation.screens.input.lost.LostInputViewModel

@Composable
fun ColorCategoryScreen(
    modifier: Modifier,
    viewModel: LostInputViewModel
) {

    val selectedCategoryId by viewModel.colorSelectedId.collectAsState()

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .padding(),
        verticalArrangement = Arrangement.Bottom,
        horizontalArrangement = Arrangement.Start,
        columns = GridCells.Adaptive(minSize = 138.dp)
    ) {
        items(colorCategories) { colorCategory ->
            CategoryCard(
                modifier = Modifier,
                categoryText = colorCategory.name,
                isSelected = colorCategory.name == selectedCategoryId,
                onCategoryClick = { viewModel.setColorSelectedIdId( colorCategory.name ) }
            )
        }
    }
}
