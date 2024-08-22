package com.example.foundit.presentation.splash

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.foundit.presentation.data.navigation.NavRoutes


@Composable
fun SplashScreen(
    forwardNavigation: String,
    navController: NavHostController
){
    val viewModel: SplashScreenViewModel = hiltViewModel()
    LaunchedEffect(true) {
        try {
            viewModel.onAppStart { isSuccess ->
                if (isSuccess) {
                    navController.navigate(NavRoutes.HOME)
                } else {
                    navController.navigate(forwardNavigation)
                }
            }
        } catch (e: Exception) {
            Log.d("Session", "$e")
        }
    }

}