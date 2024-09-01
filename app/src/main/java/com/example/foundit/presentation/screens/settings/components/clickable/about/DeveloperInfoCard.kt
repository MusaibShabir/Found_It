package com.example.foundit.presentation.screens.settings.components.clickable.about


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foundit.R
import com.example.foundit.ui.theme.githubColor
import com.example.foundit.ui.theme.instagramColor
import com.example.foundit.ui.theme.linkedInColor
import com.example.foundit.ui.theme.twitterColor


@Composable
fun DeveloperInfoCard(
    modifier: Modifier = Modifier,
    profilePicture: Painter,
    firstName: String,
    lastName: String,
    title: String,
    githubLink: String,
    linkedInLink: String,
    twitterLink: String,
    instagramLink: String,
) {
    val urlHandler: UriHandler = LocalUriHandler.current

    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp),
    ) {
        Row(
            modifier = modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .weight(.4f)
                    .padding(1.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = profilePicture,
                    contentDescription = "Developer's profile picture ",
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .size(130.dp),

                )
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .weight(.6f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 5.dp)
                ){
                    //Name & Title Column
                    Column(
                        modifier = modifier,
                        verticalArrangement = Arrangement.SpaceBetween

                    ) {
                        Row(
                            modifier = modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "$firstName $lastName",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight(900),
                                fontSize = 18.sp,
                                color = Color.Black,
                                maxLines = 1
                            )
                        }
                        Row(
                            modifier = modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight(300),
                                color = Color.Black,
                                fontStyle = FontStyle.Italic,
                                fontSize = 14.sp,
                                maxLines = 1
                            )
                        }
                    }

                }
                HorizontalDivider(
                    modifier = modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 5.dp),
                    thickness = 1.dp,
                    color = Color.Black
                )

                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ElevatedButton(
                            modifier = modifier.width(100.dp),
                            onClick = {urlHandler.openUri(githubLink)},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = githubColor,
                                contentColor = Color.White,
                            ),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 24.dp
                            )

                        ) {
                            Text(
                                text = "GitHub",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center
                            )
                        }

                        ElevatedButton(
                            modifier = modifier.width(100.dp),
                            onClick = {urlHandler.openUri(linkedInLink)},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = linkedInColor,
                                contentColor = Color.White,
                            ),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 24.dp
                            )

                        ) {
                            Text(
                                text = "LinkedIn",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center
                            )
                        }
                    }


                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        ElevatedButton(
                            modifier = modifier.width(100.dp),
                            onClick = {urlHandler.openUri(twitterLink)},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = twitterColor,
                                contentColor = Color.White,
                            ),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 24.dp
                            )

                        ) {
                            Text(
                                text = "Twitter",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center
                            )
                        }

                        ElevatedButton(
                            modifier = modifier.width(100.dp),
                            onClick = {urlHandler.openUri(instagramLink)},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = instagramColor,
                                contentColor = Color.White,
                            ),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 24.dp
                            )

                        ) {
                            Text(
                                text = "Instagram",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

            }

        }


    }

}


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewDeveloperInfo() {
    val profilePicture = painterResource(id = R.drawable.ic_launcher_background)
    DeveloperInfoCard(
        modifier = Modifier,
        profilePicture = profilePicture,
        firstName = "Musaib",
        lastName = "Shabir Dar",
        title = "Android Developer",
        githubLink = "",
        linkedInLink = "",
        twitterLink = "",
        instagramLink = ""
    )
}
