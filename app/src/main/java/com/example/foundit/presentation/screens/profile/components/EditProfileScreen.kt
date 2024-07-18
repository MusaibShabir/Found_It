package com.example.foundit.presentation.screens.profile.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardCapitalization
import coil.compose.AsyncImage
import com.example.foundit.R.drawable.ic_launcher_background


@Composable
fun EditProfileScreen() {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var profilePicture by remember { mutableStateOf<Uri?>(null) }

    val profilePicturePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {uri -> profilePicture = uri }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Edit Profile",
            style = MaterialTheme.typography.titleLarge,
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(46.dp))

        // Profile picture Input Field
        AsyncImage(
            model = profilePicture ?: ic_launcher_background,
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape)
                .clickable {
                    // profile picture change logic
                    profilePicturePickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(26.dp))


        // First name Input Field
        TextField(
            value = firstName,
            onValueChange = {
                firstName = it.filter { char -> char.isLetter() || char.isWhitespace() }
            },
            singleLine = true,
            label = { Text("First name", fontSize = 16.sp, fontWeight = FontWeight.Medium) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                KeyboardCapitalization.Words // Capitalize the first letter of each word
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Last name Input Field
        TextField(
            value = lastName,
            onValueChange = {
                lastName = it.filter { char -> char.isLetter() || char.isWhitespace() }
            },
            singleLine = true,
            label = { Text("Last name", fontSize = 16.sp, fontWeight = FontWeight.Medium) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                KeyboardCapitalization.Words // Capitalize the first letter of each word
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row (
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ){
            // Save Button
            Button(
                onClick = {
                    // Handle cancel action

                },
                enabled = firstName.isNotBlank() && lastName.isNotBlank(),
            ) {
                Text("Cancel", fontSize = 20.sp, modifier = Modifier.padding(5.dp,2.dp))
            }

            // Save Button
            Button(
                onClick = {
                    // Handle save action

                },
                enabled = firstName.isNotBlank() && lastName.isNotBlank(),
            ) {
                Text("Save Changes", fontSize = 20.sp, modifier = Modifier.padding(5.dp,2.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewEditProfileScreen() {
    EditProfileScreen()
}

