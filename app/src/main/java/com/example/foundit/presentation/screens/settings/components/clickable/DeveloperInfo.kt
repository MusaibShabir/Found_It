package com.example.foundit.presentation.screens.settings.components.clickable

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.foundit.R
import com.example.foundit.presentation.data.navigation.NavRoutes


@Composable
fun DeveloperInfo(
    modifier: Modifier = Modifier,
    profilePicture: Painter,
    firstName: String,
    lastName: String,
    title: String
) {
    val urlHandler: UriHandler = LocalUriHandler.current

        Card(
        shape = RoundedCornerShape(15.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(16.dp),
    ) {
        Row(
            modifier = modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .weight(.4f)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = profilePicture,
                    contentDescription = "Developer's profile picture ",
                    alignment = Alignment.Center,
                    modifier = Modifier.clip(shape = CircleShape)
                )
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .weight(.6f)
                    .padding(top = 5.dp)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
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
                        fontWeight = FontWeight(600),
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
                        fontWeight = FontWeight(200),
                        color = Color.Black,
                        maxLines = 1
                    )
                }
                Spacer(modifier = modifier.height(10.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(15.dp),
                        color = Color.LightGray,
                        modifier = Modifier
                            .height(20.dp)
                            .width(60.dp)
                    ) {
                        Row(
                            modifier = modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.AddCircleOutline,
                                contentDescription = "GitHub",
                                tint = Color.Black,
                                modifier = Modifier.size(10.dp)


                            )
                            Spacer(modifier = modifier.width(3.dp))

                            Text(
                                text = "GitHub",
                                color = Color.Black,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = modifier.clickable {
                                    urlHandler.openUri("https://github.com/MusaibShabir")
                                }
                            )
                        }
                    }

                    Surface(
                        shape = RoundedCornerShape(15.dp),
                        color = Color.LightGray,
                        modifier = Modifier
                            .height(20.dp)
                            .width(60.dp)
                    ) {
                        Row(
                            modifier = modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,

                            ) {
                            Icon(
                                imageVector = Icons.Filled.AddCircleOutline,
                                contentDescription = "LinkedIn",
                                tint = Color.Black,
                                modifier = Modifier.size(10.dp)

                            )
                            Spacer(modifier = modifier.width(3.dp))

                            Text(
                                text = "LinkedIn",
                                color = Color.Black,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = modifier
                                    .clickable {
                                        urlHandler.openUri(
                                            "https://www.linkedin.com/in/musaib-shabir-49179b244/"
                                        )
                                    }
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
    val profilePicture = painterResource(id =R.drawable.ic_launcher_background)
    DeveloperInfo(
        modifier = Modifier,
        profilePicture = profilePicture,
        firstName = "Musaib",
        lastName = "Shabir Dar",
        title = "Android Developer"
    )
}



