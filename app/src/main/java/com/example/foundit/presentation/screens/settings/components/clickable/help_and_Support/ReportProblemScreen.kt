package com.example.foundit.presentation.screens.settings.components.clickable.help_and_Support

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foundit.presentation.common.TheTopAppBar
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ReportBugScreen(
    modifier:Modifier = Modifier,
    navController: NavHostController,
    //onSubmitReport: ((String, Uri?) -> Unit)? // Modify the parameters based on your needs
) {
    var issueDescription by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    Scaffold(
        modifier= modifier,
        topBar ={
            TheTopAppBar(title = "Report a problem", navController = navController)
        }
    ){innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
        ) {
            TextField(
                value = issueDescription,
                onValueChange = { issueDescription = it },
                label = { Text("Issue Description") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    imagePickerLauncher.launch("image/*")
                },
                modifier = Modifier.padding(16.dp),
            ) {
                Text(text = "Attach Screenshot")
            }
            selectedImageUri?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Selected Image: $it")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                   //onSubmitReport(issueDescription, selectedImageUri)
                },
                enabled = issueDescription.isNotBlank(),
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                Text(text = "Submit Report")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewReportProblemScreen(){
    ReportBugScreen(navController = NavHostController(LocalContext.current))
}
