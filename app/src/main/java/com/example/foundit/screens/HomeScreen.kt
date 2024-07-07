package com.example.foundit.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foundit.R
import com.example.foundit.components.AppName
import com.example.foundit.components.Greetings
import com.example.foundit.components.MainCard
import com.example.foundit.ui.theme.MainGreen
import com.example.foundit.ui.theme.MainRed


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(modifier: Modifier) {
    Scaffold(
        modifier = modifier,
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(.9f)
                //.padding(innerPadding)
                .padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppName(modifier = modifier)
            Greetings(modifier = modifier, name =  "Musaib Shabir")

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(vertical = 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                MainCard(
                    modifier = Modifier,
                    cardHeading = R.string.lost_card_heading,
                    cardTitle = R.string.lost_card_sub_title,
                    buttonName = R.string.lost_card_button,
                    cardColor = MainRed
                )

                MainCard(
                    modifier = Modifier,
                    cardHeading = R.string.found_card_heading,
                    cardTitle = R.string.found_card_sub_title,
                    buttonName = R.string.found_card_button,
                    cardColor = MainGreen
                )

            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_2")
fun PreviewHomeScreen() {
    HomeScreen(modifier = Modifier)
}