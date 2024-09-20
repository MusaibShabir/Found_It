package com.example.foundit.presentation.screens.input.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChildCategoryScreen2(
    modifier: Modifier
) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .padding(),
        verticalArrangement = Arrangement.Bottom,
        columns = GridCells.Adaptive(minSize = 110.dp)
    ) {
        /*
        items(childCategories) { parentCategory ->
            CategoryCard(
                modifier = Modifier,
                categoryText = parentCategory.name,

                onCategoryClick = { }
            )
        }

         */
    }

    Spacer(modifier = Modifier.height(30.dp))

    Text(
        text = "This Is Screen 2"
    )
}
