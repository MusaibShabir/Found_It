package com.example.foundit.presentation.screens.home


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foundit.R
import com.example.foundit.presentation.screens.home.components.AppName
import com.example.foundit.presentation.screens.home.components.Greetings
import com.example.foundit.presentation.screens.home.components.MainCard
import com.example.foundit.ui.theme.MainGreen
import com.example.foundit.ui.theme.MainRed


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(modifier: Modifier) {
    Scaffold {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppName(modifier = modifier)
            Greetings(modifier = modifier, name =  "Musaib Shabir")
            HorizontalDivider(
                thickness = 1.dp,
                modifier = modifier
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            )
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(vertical = 20.dp)
                    .verticalScroll(rememberScrollState(), true),
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