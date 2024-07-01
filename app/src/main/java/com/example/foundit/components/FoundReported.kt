package com.example.foundit.components


import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foundit.R

@Composable
fun FoundReportedCard(
    modifier: Modifier
) {
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(100.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            VerticalDivider(
                color = Color.Black,
                modifier = Modifier
                    .width(2.dp)
                    .height(70.dp)
                    .align(Alignment.Center)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {

                ScoreSection(modifier = Modifier, sectionHeading = R.string.score_card_section_heading_left, sectionScore = 10)
                ScoreSection(modifier = Modifier, sectionHeading = R.string.score_card_section_heading_right, sectionScore = 3)
            }
        }

    }
}




@Composable
fun ScoreSection(
    modifier: Modifier,
    @StringRes
    sectionHeading: Int,
    sectionScore: Int
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ){
        Text(
            text = stringResource(id = sectionHeading),
            fontSize = 20.sp,
            fontWeight = FontWeight.Light
        )
        Spacer(modifier = modifier.height(5.dp))

        Text(
            text = sectionScore.toString(),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewFoundReported() {
    FoundReportedCard(
        modifier = Modifier
    )
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewScoreSection() {
    ScoreSection(
        modifier = Modifier,
        sectionHeading = R.string.score_card_section_heading_left,
        sectionScore = 10
    )
}