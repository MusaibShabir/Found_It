package com.example.foundit.presentation.screens.input.common.components

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
import com.example.foundit.presentation.screens.input.data.colorCategories
import com.example.foundit.presentation.screens.input.lost.LostInputViewModel
import com.example.foundit.ui.theme.MainGreen

@Composable
fun ColorCategoryScreen(
    modifier: Modifier,
    viewModel: LostInputViewModel
) {

    val selectedCategoryId by viewModel.colorSelectedId.collectAsState()

    var colorCategoryTopHeading by remember { mutableStateOf("") }

    val cardType by viewModel.cardType.collectAsState()
    when(cardType){
        0 -> colorCategoryTopHeading = "Select the color of your lost item."
        1 -> colorCategoryTopHeading = "Select the color of your found item."
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
                        text = colorCategoryTopHeading,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
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
                columns = GridCells.FixedSize(124.dp)
            ) {
                items(colorCategories) { colorCategory ->
                    CategoryCard(
                        modifier = Modifier,
                        borderColor = Color.Black,
                        categoryText = colorCategory.name,
                        isSelected = colorCategory.name == selectedCategoryId,
                        onCategoryClick = { viewModel.setColorSelectedIdId( colorCategory.name ) }
                    )
                }
            }
        }
    }

}
