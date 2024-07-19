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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.foundit.R.drawable.ic_launcher_background
import com.example.foundit.presentation.common.TheTopAppBar


@Composable
fun EditProfileScreen(
    modifier: Modifier,
    navController: NavController
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var profilePicture by remember { mutableStateOf<Uri?>(null) }

    val profilePicturePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> profilePicture = uri }
    )

    Scaffold(
        topBar = { TheTopAppBar(title = "Edit Profile", navController = navController) }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = modifier.height(46.dp))

            // Profile picture Input Field
            AsyncImage(
                model = profilePicture ?: ic_launcher_background,
                contentDescription = "Profile Picture",
                modifier = modifier
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

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // First name Input Field
                TextField(
                    value = firstName,
                    onValueChange = {
                        firstName = it.filter { char -> char.isLetter() || char.isWhitespace() }
                    },
                    singleLine = true,
                    label = {
                        Text(
                            "First name",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    },
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

                Spacer(modifier = modifier.height(16.dp))

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
                        KeyboardCapitalization.Words
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent
                    )
                )
            }

            Spacer(modifier = modifier.height(30.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier.fillMaxWidth()
            ) {

                // Cancel Button
                Button(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Text(
                        text = "Cancel",
                        fontSize = 20.sp,
                        modifier = modifier
                            .padding(5.dp, 2.dp)
                    )
                }

                // Save Button
                Button(
                    onClick = {
                        navController.popBackStack()
                    },
                    enabled = firstName.isNotBlank() && lastName.isNotBlank(),
                ) {
                    Text(
                        text = "Save Changes",
                        fontSize = 20.sp,
                        modifier = modifier
                            .padding(5.dp, 2.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewEditProfileScreen() {
    EditProfileScreen(
        modifier = Modifier,
        navController = NavController(LocalContext.current)
    )
}

