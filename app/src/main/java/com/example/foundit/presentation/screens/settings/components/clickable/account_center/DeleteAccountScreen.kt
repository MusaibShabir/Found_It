package com.example.foundit.presentation.screens.settings.components.clickable.account_center

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.foundit.presentation.common.TheTopAppBar
import com.example.foundit.presentation.data.navigation.NavRoutes
import com.example.foundit.presentation.screens.settings.components.clickable.DeleteAccountViewModel
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException

@Composable
fun DeleteAccountScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val viewModel: DeleteAccountViewModel = hiltViewModel()
    val context = LocalContext.current

    Scaffold(
        topBar ={
            TheTopAppBar(title = "Delete Account", navController = navController)
        }
    ){innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = "Are you sure you want to delete your account? This action is irreversible.",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.deleteAccount { isSuccess, e ->
                        if (isSuccess) {
                            Toast.makeText(context, "Account deleted successfully", Toast.LENGTH_SHORT).show()
                            navController.navigate(NavRoutes.SPLASH)
                        } else {
                            val errorMessage = when (e) {
                                is FirebaseNetworkException -> "Network issue. Please check your connection and try again."
                                is FirebaseAuthRecentLoginRequiredException -> "Please logout and re-authenticate to delete your account."
                                else -> "An unexpected error occurred."
                            }
                            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                modifier = Modifier.padding(start = 12.dp)
            ) {
                Text(text = "Delete Account")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDeleteAccountScreen(){
    DeleteAccountScreen(navController = NavHostController(LocalContext.current))
}