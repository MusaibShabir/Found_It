package com.example.foundit.presentation.screens.settings.components.clickable.help_and_Support

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.foundit.presentation.common.TheTopAppBar

@Composable
fun ContactSupportScreen(
    modifier:Modifier = Modifier,
    navController: NavHostController
) {
    Scaffold(
        modifier= modifier,
        topBar ={
            TheTopAppBar(title = "Contact Support", navController = navController)
        }
    ){innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
        ) {
            Text(
                text = "Need help or have questions? Our support team is here to assist you.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /* Launch email intent or in-app email client */ },
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(text = "Email Support")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { /* Launch phone dialer */ },
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(text = "Call Support")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Support Hours:\n\n" +
                        "Monday to Friday: 9 AM to 6 PM\n" +
                        "Saturday: 10 AM to 4 PM\n" +
                        "Sunday and Public Holidays: Closed",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /* Navigate to FAQ screen */ }
            ) {
                Text(text = "Go to FAQ")
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewContactSupportScreen(){
    ContactSupportScreen(navController = NavHostController(LocalContext.current))
}
