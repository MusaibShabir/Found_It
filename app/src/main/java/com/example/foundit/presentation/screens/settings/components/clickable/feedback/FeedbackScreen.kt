package com.example.foundit.presentation.screens.settings.components.clickable.feedback

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foundit.presentation.common.TheTopAppBar

@Composable
fun FeedbackScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    //onSubmitFeedback: (String) -> Unit // Parameters for feedback content and email
) {
    var feedbackContent by remember { mutableStateOf("") }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TheTopAppBar(title = "Feedback", navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Text(
                text = "We value your feedback. Please share your thoughts and suggestions to help us improve.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = feedbackContent,
                onValueChange = { feedbackContent = it },
                label = { Text("Your Feedback") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 5,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    //onSubmitFeedback(feedbackContent)
                },
                enabled = feedbackContent.isNotBlank(),
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(text = "Submit Feedback")
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewFeedbackScreen(){
    FeedbackScreen(navController = NavHostController(LocalContext.current))
}