package com.example.foundit.presentation.screens.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foundit.R
import com.example.foundit.ui.theme.MainGreen

// UI-Only Composable
@Composable
fun MemberSinceCardContent(
    modifier: Modifier = Modifier,
    memberSince: String?
) {
    ElevatedCard(
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = MainGreen),
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
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.member_card_heading),
                    fontWeight = FontWeight.Light,
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
            ) {
                if (memberSince != null) {
                    Text(
                        text = memberSince,
                        fontSize = 22.sp,
                        color = Color.White,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 5.sp,
                    )
                } else {
                    CircularProgressIndicator()
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = false)
fun PreviewMemberSince() {
    MemberSinceCardContent(
        modifier = Modifier,
        memberSince = "10 - June - 2024"


    )
}

