package com.example.foundit.presentation.screens.profile.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foundit.R
import com.example.foundit.presentation.screens.profile.ProfileViewModel

@Composable
fun ScoreCard(
    modifier: Modifier,
    viewModel: ProfileViewModel
) {
    val profileData by viewModel.profileData.collectAsState()
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(100.dp),
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            VerticalDivider(
                color = Color.Black,
                modifier = modifier
                    .width(2.dp)
                    .height(70.dp)
                    .align(Alignment.Center)
            )
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
            ) {


                profileData?.let {

                    //Found Score Section (which is to the Left)
                    ScoreSection(
                        modifier = modifier,
                        sectionHeading = R.string.score_card_section_heading_left,
                        sectionScore = it.totalFound
                    )

                    //Reported Score Section (which is to the Right)
                    ScoreSection(
                        modifier = modifier,
                        sectionHeading = R.string.score_card_section_heading_right,
                        sectionScore = it.totalReported
                    )

                }?: run {
                CircularProgressIndicator()
            }




            }
        }

    }
}


/*
@Composable
@Preview(showBackground = true, showSystemUi = false)
fun PreviewFoundReported() {
    ScoreCard(
        modifier = Modifier,
        foundScore = 10,
        reportedScore = 5
    )
}

 */


//Child Function of the ScoreCard
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
            fontSize = 18.sp,
            fontWeight = FontWeight.Light,
        )
        Spacer(modifier = modifier.height(5.dp))

        Text(
            text = sectionScore.toString(),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

/*
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewScoreSection() {
    ScoreSection(
        modifier = Modifier,
        sectionHeading = R.string.score_card_section_heading_left,
        sectionScore = 10
    )
}

 */