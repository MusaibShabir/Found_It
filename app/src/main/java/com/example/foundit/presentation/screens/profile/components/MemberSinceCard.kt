package com.example.foundit.presentation.screens.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foundit.R

@Composable
fun MemberSinceCard(
    modifier: Modifier,
    date: String //to be changed later to LocalDate
) {
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = modifier
            .padding(16.dp)
            .height(100.dp)
            .fillMaxWidth(),

        ) {
        Column(
            modifier = modifier
                .padding(vertical = 16.dp, horizontal = 24.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.member_card_heading),
                    fontWeight = FontWeight.Light,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text =  date, //"10 - June - 2024",
                    fontSize = 22.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = false)
fun PreviewMemberSince() {
    MemberSinceCard(
        modifier = Modifier,
        date = "10 - June - 2024"
    )
}