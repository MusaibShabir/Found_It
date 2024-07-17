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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.foundit.R


@Composable
fun EditProfileScreen() {
    var firstName by remember { mutableStateOf("Musaib") }
    var lastName by remember { mutableStateOf("Shabir") }
    var email by remember { mutableStateOf("musaibShabir145@gmail.com") }
    var phoneNumber by remember { mutableStateOf("9545652895") }
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
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(56.dp))

        // Profile picture Input Field
        AsyncImage(
            model = profilePicture ?: R.drawable.ic_launcher_background,
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
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            singleLine = true,
            label = { Text("First name", fontSize = 16.sp, fontWeight = FontWeight.Medium) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Last name Input Field
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            singleLine = true,
            label = { Text("Last name", fontSize = 16.sp, fontWeight = FontWeight.Medium) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Email Input Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            singleLine = true,
            label = { Text("Email", fontSize = 16.sp, fontWeight = FontWeight.Medium) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Phone number Input Field
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            singleLine = true,
            label = { Text("Phone Number", fontSize = 16.sp, fontWeight = FontWeight.Medium) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))


        // Save Button
        Button(
            onClick = {
                // Handle save action
                println("First name: $firstName, First name: $lastName, Email: $email, Phone Number: $phoneNumber")
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Save", fontSize = 20.sp, modifier = Modifier.padding(5.dp,2.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewEditProfileScreen() {
    EditProfileScreen()
}

