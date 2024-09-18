package com.example.foundit.presentation.screens.input.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CategoryCard(
    modifier: Modifier,
    categoryText: String,
    onCategoryClick: () -> Unit
) {

    // State to track the selected category
    var isSelected by remember { mutableStateOf(false) }

    OutlinedCard(
        modifier = modifier
            .width(IntrinsicSize.Min)
            .height(48.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(24.dp),
        onClick = {
            isSelected = !isSelected
            onCategoryClick()
                  },
        border = BorderStroke(
            width = 1.dp ,
            color = if(isSelected) Color.Blue else Color.Black
        ),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = categoryText,
                fontStyle = FontStyle.Normal,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                maxLines = 1
                )
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCategoryCard() {
    CategoryCard(
        modifier = Modifier,
        categoryText = "Category",
        onCategoryClick = {}
    )
}