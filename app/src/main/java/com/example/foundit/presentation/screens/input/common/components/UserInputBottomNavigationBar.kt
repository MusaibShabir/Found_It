package com.example.foundit.presentation.screens.input.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserInputBottomNavigationBar(
    modifier: Modifier = Modifier,
    onCancelOrBackButtonText: String,
    onCancelOrBackClick:  () ->  Unit,
    onNextClick: () -> Unit
) {

    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        elevation = CardDefaults.elevatedCardElevation(2.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.2f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(36.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            // Cancel or Back Button
            Button(
                onClick = onCancelOrBackClick,
                modifier = modifier
                    .fillMaxHeight()
                    .width(128.dp)
            ) {
                Text(
                    text = onCancelOrBackButtonText,
                    fontSize = 18.sp
                )
            }

            // Next Button
            Button(
                onClick = onNextClick,
                modifier = modifier
                    .fillMaxHeight()
                    .width(128.dp)
            ) {
                Text(
                    text = "Next",
                    fontSize = 18.sp
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = false, device = "id:pixel_2")
@Composable
fun PreviewInputBottomNavigationBar() {
    UserInputBottomNavigationBar(
        modifier = Modifier,
        onCancelOrBackClick = {},
        onCancelOrBackButtonText = "Cancel",
        onNextClick = {}
    )

}