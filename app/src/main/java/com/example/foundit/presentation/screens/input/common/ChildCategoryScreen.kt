package com.example.foundit.presentation.screens.input.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foundit.presentation.screens.input.common.components.CategoryCard
import com.example.foundit.presentation.screens.input.data.childCategories
import com.example.foundit.presentation.screens.input.lost.LostInputViewModel
import com.example.foundit.ui.theme.MainGreen


@Composable
fun ChildCategoryScreen(
    modifier: Modifier,
    viewModel: LostInputViewModel
) {

    val selectedCategoryIds by viewModel.selectedChildCategoryIds.collectAsState()


    val cardType by viewModel.cardType.collectAsState()

    var parentCategoryTopHeading by remember { mutableStateOf("") }
    when(cardType){
        0 -> parentCategoryTopHeading = "Select the categories below that best describe the item you have lost. You can choose multiple categories."
        1 -> parentCategoryTopHeading = "Select the categories below that best describe the item you have found. You can choose multiple categories."
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            OutlinedCard(
                modifier = modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MainGreen)
            ) {
                Row(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = parentCategoryTopHeading,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Left,
                    )
                }
            }

        }
        HorizontalDivider(modifier = modifier.padding(vertical = 10.dp))

        OutlinedCard(
            modifier = modifier
                .fillMaxSize()
                .padding(vertical = 18.dp),
            colors = CardDefaults.cardColors(containerColor = MainGreen.copy(alpha = .25f)),
        ) {
            LazyVerticalGrid(
                modifier = modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalArrangement = Arrangement.Start,
                columns = GridCells.Adaptive(minSize = 118.dp)
            ) {
                items(childCategories) { childCategory ->
                    CategoryCard(
                        modifier = Modifier,
                        borderColor = Color.Black,
                        categoryText = childCategory.name,
                        isSelected = childCategory.id in selectedCategoryIds,
                        onCategoryClick = { viewModel.toggleChildCategorySelection( childCategory.id) },
                    )
                }
            }
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

