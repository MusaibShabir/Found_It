package com.example.foundit.presentation.screens.settings.components.clickable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foundit.presentation.common.TheTopAppBar

@Composable
fun PrivacyPolicyScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TheTopAppBar(title = "Privacy Policy", navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = """
                    Privacy Policy
                    
                    Effective Date: [Insert Date]
                    
                    Welcome to "Found it." Your privacy is critically important to us. This Privacy Policy explains how we collect, use, and protect your personal information when you use our app.
                    
                    Information We Collect
                    •	Personal Information: When you sign up using Google, we collect your phone number, email address, and username.
                    •	Location Data: We collect your GPS location to enhance the accuracy of our AI matching model.
                    •	Media: We may collect images and videos that you upload to help identify lost items.
                    
                    How We Use Your Information
                    •   Matching Lost and Found Items: Your data helps us to match lost items with found items using our AI model.
                    •	Notifications: We use your contact details to notify you when a potential match is found.
                    •	Improving Our Services: Your data helps us to enhance our app’s functionality and user experience.
                    
                    Data Sharing and Security
                    •	No Sharing with Third Parties: We do not share your personal information with any other users or third parties.
                    •	Data Security: We implement robust security measures to protect your data from unauthorized access.
                    
                    Your Rights
                    •	Access and Correction: You can access and update your personal information within the app.
                    •	Deletion: You can request the deletion of your personal data at any time.
                    
                    Contact Us
                    If you have any questions or concerns about our Privacy Policy, please contact us at [support email].
                """.trimIndent(),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview
@Composable
fun PrivacyPolicyScreenPreview() {
    PrivacyPolicyScreen(navController = NavHostController(LocalContext.current))
}