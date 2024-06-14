package com.example.foundit.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FoundReportedCard() {
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Found", fontSize = 20.sp,fontWeight = FontWeight.Light)
                    Text(text = "4", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }

                VerticalDivider(color= Color.White, modifier =
                Modifier
                    .width(2.dp)
                    .height(70.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Reported", fontSize = 20.sp,fontWeight = FontWeight.Light)
                    Text(text = "10", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewFoundReported() {
    FoundReportedCard()
}