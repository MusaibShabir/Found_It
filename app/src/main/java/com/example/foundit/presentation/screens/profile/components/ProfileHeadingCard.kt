package com.example.foundit.presentation.screens.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foundit.R


// UI-Only Composable
@Composable
fun ProfileHeadingCard(
    modifier: Modifier = Modifier,
    profilePicture: Painter,
    firstName: String,
    lastName: String,
    profileCountryCode: Int,
    profileId: Int,
    onEditProfileClick: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(16.dp),
    ) {
        Row (modifier = modifier.fillMaxSize()){
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .weight(.4f)
                    .padding(16.dp)
            ) {

                Image(
                    painter = profilePicture,
                    contentDescription = "",
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .clip(shape = CircleShape))
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .weight(.6f)
                    .padding(top = 5.dp)
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                    Text(
                        text = "$firstName $lastName",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight(600),
                        maxLines = 1,
                    )
                    Row (
                        modifier = modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {

                        if (profileCountryCode == 91) {

                            Image(
                                painter = painterResource(id = R.drawable.flag_in),
                                contentDescription = "Flag",
                                modifier = Modifier
                                    .size(14.dp)

                            )
                            Text(
                                text = profileCountryCode.toString(),
                                maxLines = 1,
                                fontSize = 13.sp,
                                modifier = modifier.padding(start = 5.dp)
                            )
                        }
                    }

                    Button(
                        onClick = { onEditProfileClick() },

                    ) {
                    }
            }

        }


    }

}



/*
// ViewModel Composable
@Composable
fun ProfileHeadingCard(
    modifier: Modifier,
    profilePicture: Painter,
    profileCountryCode: String,
    navController: NavController,
    viewModel: ProfileViewModel
) {
    val profileData by viewModel.profileData.collectAsState()

    ProfileHeadingCardContent(
        modifier = modifier,
        profilePicture = profilePicture,
        profileData = profileData,
        profileCountryCode = profileCountryCode,
        onEditProfileClick = { navController.navigate(NavRoutes.EDIT_PROFILE) }
    )
}

 */



@Composable
@Preview(showBackground = true, showSystemUi = false)

fun ProfileHeadingCardPreview() {
    ProfileHeadingCard(
        modifier = Modifier,
        profilePicture = painterResource(id = R.drawable.ic_launcher_background),
        profileCountryCode = 91,
        firstName = "Musaib",
        lastName = "Shabir",
        profileId = 234567890,
        onEditProfileClick = { /*TODO*/ },


    )

}

