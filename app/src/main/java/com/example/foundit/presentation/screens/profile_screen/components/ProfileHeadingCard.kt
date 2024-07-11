package com.example.foundit.presentation.screens.profile_screen.components

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foundit.R


@Composable
fun ProfileHeadingCard(
    modifier: Modifier,
    profileName: String,
    profilePicture: Painter,
    profileCountryFlag: Painter,
    profileCountryCode: String,
    profileId: Long,
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
                    .padding(16.dp)
            ) {
                Text(
                    text = profileName,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight(500),
                    maxLines = 1,


                    )
                Row (modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Image(
                        painter = profileCountryFlag,
                        contentDescription = "Flag",
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = modifier.width(5.dp))
                    Text(
                        text = profileCountryCode,
                        maxLines = 1,
                        fontSize = 13.sp,

                    )
                }


                Text(
                    text = "#$profileId",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight(300),
                    fontSize = 12.sp
                )

                Button(
                    onClick = { /*TODO*/ },
                    elevation = buttonElevation(defaultElevation = 20.dp, pressedElevation = 25.dp),
                    colors = buttonColors(containerColor = Color.Gray),
                    modifier = modifier.height(35.dp)

                ) {
                    Row (modifier = modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    )
                    {
                        Icon(
                            imageVector =  Icons.Filled.Edit,
                            contentDescription = "Edit",
                            tint = Color.Black,
                            modifier = Modifier.size(11.dp)

                        )
                        Spacer(modifier = modifier.width(5.dp))
                        Text(
                            text = "Edit Profile",
                            color = Color.Black,
                            fontSize = 11.sp,

                        )
                    }

                }

            }


        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = false)

fun ProfileHeadingCardPreview() {
    ProfileHeadingCard(
        modifier = Modifier,
        profileName = "Musaib Shabir",
        profilePicture = painterResource(id = R.drawable.ic_launcher_background),
        profileCountryFlag = painterResource(id = R.drawable.flag_in),
        profileCountryCode = "IND",
        profileId = 234567890
    )

}