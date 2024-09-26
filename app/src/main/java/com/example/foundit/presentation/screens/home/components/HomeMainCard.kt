package com.example.foundit.presentation.screens.home.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.ButtonDefaults.buttonElevation
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.foundit.R
import com.example.foundit.presentation.data.navigation.NavRoutes

@Composable
fun MainCard(
    modifier: Modifier,
    @StringRes
    cardHeading: Int,
    cardTitle: Int,
    cardColor: Color,
    navController: NavHostController,
    forwardNavigation: String,
) {
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .height(232.dp)
            .padding(vertical = 10.dp),
        shape = RoundedCornerShape(size = 42.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
        colors = cardColors(containerColor = cardColor)
    ) {
        Column(
            modifier = modifier
                .padding(20.dp),
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_category_24),
                    contentDescription = "Category Icon",
                    tint = Color.White,
                    modifier = modifier
                        .padding(end = 10.dp)
                        .size(40.dp)
                )
                Text(
                    text = stringResource(id = cardHeading),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    fontSize = 22.sp

                )
            } // Row Scope Ends

            // Main Column Scope
            Spacer(modifier = modifier.height(8.dp))

            // Sub-Column Start
            Column (
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 200.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ){

                Text(
                    text = stringResource(id = cardTitle),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    fontSize = 13.sp,
                    fontStyle = FontStyle.Italic,
                    lineHeight = 15.sp,
                    textAlign = TextAlign.Center
                )
            } // Sub-Column Ends

            Spacer(modifier = modifier.height(8.dp))

            Row (
                modifier = modifier
                    .fillMaxSize()
                    .padding(start = 200.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Button(
                    onClick = {
                        navController.navigate(forwardNavigation)
                              },
                    elevation = buttonElevation(defaultElevation = 20.dp, pressedElevation = 25.dp),
                    colors = buttonColors(containerColor = Color.White),
                    modifier = modifier.fillMaxWidth()

                ) {
                    Text(
                        text = stringResource(id = R.string.button_text),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = cardColor,
                        fontSize = 12.sp,
                    )
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = false, device = "id:pixel_6_pro")
fun PreviewMainCard() {
    MainCard(
        modifier = Modifier,
        cardHeading = R.string.lost_card_heading,
        cardTitle = R.string.lost_card_sub_title,
        cardColor = Color.Red,
        navController = NavHostController(LocalContext.current),
        forwardNavigation = NavRoutes.ACTION_SCREEN
    )
}
