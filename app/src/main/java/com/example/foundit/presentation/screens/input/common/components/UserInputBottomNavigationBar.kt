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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foundit.ui.theme.MainGreen

@Composable
fun UserInputBottomNavigationBar(
    modifier: Modifier = Modifier,
    cancelOrBackButtonText: String,
    nextOrSubmitButtonText: String,
    onCancelOrBackClick:  () ->  Unit,
    onNextClick: () -> Unit,
    nextButtonEnabled: () -> Boolean
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
            ElevatedButton(
                onClick = onCancelOrBackClick,
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = MainGreen.copy(alpha = .8f),
                    contentColor = Color.White
                ),
                modifier = modifier
                    .fillMaxHeight()
                    .width(128.dp)
            ) {
                Text(
                    text = cancelOrBackButtonText,
                    fontSize = 18.sp
                )
            }

            // Next or Submit Button
            ElevatedButton(
                onClick = onNextClick,
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = MainGreen.copy(alpha = .8f),
                    contentColor = Color.White
                ),
                enabled = nextButtonEnabled(),
                modifier = modifier
                    .fillMaxHeight()
                    .width(128.dp)
            ) {
                Text(
                    text = nextOrSubmitButtonText,
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
        cancelOrBackButtonText = "Cancel",
        nextOrSubmitButtonText = "Next",
        onNextClick = {},
        nextButtonEnabled = {true}
    )

}