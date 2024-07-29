package com.example.foundit.presentation.screens.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.ButtonDefaults.buttonElevation
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
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
                                text = "INDIA",
                                maxLines = 1,
                                fontSize = 13.sp,
                                modifier = modifier.padding(start = 5.dp)
                            )
                        }
                    }

                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight= FontWeight.ExtraBold, fontStyle = FontStyle.Normal)) {
                                append("#")
                            }
                            append(profileId.toString())
                        },
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight(300),
                        fontStyle = FontStyle.Italic,
                        fontSize = 12.sp,
                        modifier = modifier.padding(top = 1.dp, bottom = 1.dp)
                    )

                    Button(
                        onClick = { onEditProfileClick() },
                        shape = RoundedCornerShape(15.dp),
                        elevation = buttonElevation(defaultElevation = 20.dp, pressedElevation = 25.dp),
                        colors = buttonColors(containerColor = Color.Gray),
                        modifier = modifier
                            .padding(top = 3.dp)
                            .height(35.dp)


                    ) {
                        Row (modifier = modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        )
                        {
                            Icon(
                                imageVector = Icons.Filled.Edit,
                                contentDescription = "Edit",
                                tint = Color.Black,
                                modifier = Modifier.size(11.dp)

                            )
                            Spacer(modifier = modifier.width(5.dp))

                            Text(
                                text = stringResource(id = R.string.edit_profile_button),
                                color = Color.Black,
                                fontSize = 11.sp,
                            )
                        }
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

