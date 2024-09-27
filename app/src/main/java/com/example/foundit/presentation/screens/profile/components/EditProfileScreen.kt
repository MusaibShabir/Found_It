package com.example.foundit.presentation.screens.profile.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.foundit.R.drawable.ic_launcher_background
import com.example.foundit.presentation.common.TheTopAppBar
import com.example.foundit.presentation.screens.profile.ProfileViewModel

//UI-Only Composable
@Composable
fun EditProfileScreenContent(
    modifier: Modifier,
    firstName: String,
    lastName: String,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit,
    profilePicture: Uri?,
    onProfilePictureChange: (Uri?) -> Unit,
    navController: NavController
) {
    //var profilePicture by remember { mutableStateOf(profilePic) }
    val profilePicturePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                //profilePicture = uri
                onProfilePictureChange(uri)// Update profile picture only if a valid URI is selected
            }
        }
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
                    onValueChange = { onFirstNameChange(it.filter { char -> char.isLetter() }) },
                    singleLine = true,
                    label = {
                        Text(
                            text = "First name",
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        errorContainerColor = MaterialTheme.colorScheme.onError
                    )
                )

                Spacer(modifier = modifier.height(16.dp))

                // Last name Input Field
                TextField(
                    value = lastName,
                    onValueChange = { onLastNameChange(it.filter { char -> char.isLetter() }) },
                    singleLine = true,
                    label = { Text(text = "Last name") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        errorContainerColor = MaterialTheme.colorScheme.onError
                    )
                )
            }

            Spacer(modifier = modifier.height(30.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 80.dp)
            ) {

                // Cancel Button
                Button(
                    onClick = { onCancelClick() }
                ) {
                    Text(
                        text = "Cancel",
                        fontSize = 20.sp,
                        modifier = modifier
                            .padding(5.dp, 2.dp)
                    )
                }

                //Save Button
                Button(
                    onClick = { onSaveClick() },
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


//ViewModel Composable
@Composable
fun EditProfileScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: ProfileViewModel
) {
//    val userName = viewModel.userName
//    val profilePictures: Uri? = viewModel.profilePicture
    val userFirstName by viewModel.userFirstNames.collectAsState()
    val userLastName by viewModel.userLastNames.collectAsState()
    val profilePictures by viewModel.profilePicture.collectAsState()


    //Profile Heading Card
    var profileFirstName by remember { mutableStateOf(userFirstName) }
    var profileLastName by remember { mutableStateOf(userLastName) }
//    var profileFirstName by remember { mutableStateOf(profileData?.firstName ?: "") }
//    var profileLastName by remember { mutableStateOf(profileData?.lastName ?: "") }
    var profilePicture by remember { mutableStateOf(profilePictures) }


    EditProfileScreenContent(
        modifier = modifier,
        firstName = profileFirstName,
        lastName = profileLastName,
        onFirstNameChange = { profileFirstName = it },
        onLastNameChange = { profileLastName = it },
        onCancelClick = { navController.popBackStack() },
        onSaveClick = {
            viewModel.updateProfileData(firstName = profileFirstName, lastName = profileLastName, profilePicture = profilePicture)
            navController.popBackStack()
        },
        profilePicture = profilePicture,
        onProfilePictureChange = { profilePicture = it},
        navController = navController,
    )

}




//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PreviewEditProfileScreen() {
//    EditProfileScreenContent(
//        modifier = Modifier,
//        firstName = "Musaib",
//        lastName = "Shabir",
//        onFirstNameChange = {},
//        onLastNameChange = {},
//        onCancelClick = {},
//        onSaveClick = {},
//        navController = NavController(LocalContext.current),
//        profilePic = null
//    )
//}

