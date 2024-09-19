package com.example.foundit.presentation.screens.input

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foundit.presentation.common.TheTopAppBar
import com.example.foundit.presentation.data.navigation.NavRoutes
import com.example.foundit.presentation.screens.currentRoute
import com.example.foundit.presentation.screens.input.common.ChildCategoryScreen
import com.example.foundit.presentation.screens.input.common.ParentCategoryScreen
import com.example.foundit.presentation.screens.input.common.components.AreYouSureToCancelAlertBox
import com.example.foundit.presentation.screens.input.common.components.ChildCategoryScreen2
import com.example.foundit.presentation.screens.input.common.components.ItemDescriptionScreen
import com.example.foundit.presentation.screens.input.common.components.UserInputBottomNavigationBar


@Composable
fun UserItemInputScreen(
    modifier: Modifier,
    navController: NavController
) {
    var showAlertDialogBox by remember { mutableStateOf(false) }
    val navControllerForUserInputScreen = rememberNavController()
    val currentRoute = currentRoute(navControllerForUserInputScreen)
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TheTopAppBar(title = "Report Lost Item", navController = navController) },
        bottomBar = {
            UserInputBottomNavigationBar(
                modifier = modifier,

                cancelOrBackButtonText = when(currentRoute) {
                    NavRoutes.PARENT_CATEGORY_SCREEN -> { "Cancel" }
                    else -> { "Back" }
                },

                nextorSubmitButtonText = when(currentRoute) {
                    NavRoutes.ITEM_DESCRIPTION_SCREEN -> { "Submit" }
                    else -> { "Next" }
                },

                onCancelOrBackClick = {
                    when(currentRoute) {
                        NavRoutes.PARENT_CATEGORY_SCREEN -> {
                            showAlertDialogBox = true
                        }
                        else -> { navControllerForUserInputScreen.popBackStack() }
                    }

                                      },
                onNextClick = {
                    when (currentRoute) {
                    NavRoutes.PARENT_CATEGORY_SCREEN -> {
                        navControllerForUserInputScreen.navigate(NavRoutes.CHILD_CATEGORY_SCREEN)
                    }
                    NavRoutes.CHILD_CATEGORY_SCREEN -> {
                        navControllerForUserInputScreen.navigate(NavRoutes.CHILD_CATEGORY_SCREEN2)
                    }
                    NavRoutes.CHILD_CATEGORY_SCREEN2 -> {
                        navControllerForUserInputScreen.navigate(NavRoutes.ITEM_DESCRIPTION_SCREEN)
                    }
                    else -> {

                    }
                }})
        }

    ){ paddingValues ->

        NavHost(
            navController = navControllerForUserInputScreen,
            startDestination = NavRoutes.PARENT_CATEGORY_SCREEN,
            modifier = modifier.padding(paddingValues)
        ) {

            composable(NavRoutes.PARENT_CATEGORY_SCREEN) {
                ParentCategoryScreen(
                    modifier = modifier
                )
            }

            composable(NavRoutes.CHILD_CATEGORY_SCREEN) {
                ChildCategoryScreen(modifier = modifier)
            }

            composable(NavRoutes.CHILD_CATEGORY_SCREEN2) {
                ChildCategoryScreen2(modifier = modifier)
            }

            composable(NavRoutes.ITEM_DESCRIPTION_SCREEN) {
                ItemDescriptionScreen(modifier = modifier)
            }
        }
    }

    // Logic to Show Alert Dialog
    if (showAlertDialogBox) {
        AreYouSureToCancelAlertBox(
            modifier = modifier,
            onDismissRequest = { showAlertDialogBox = false },
            navController = navController
        )
    }

    // Back Handler for Swipe on the Edge of the Screen
    BackHandler(
        enabled = currentRoute == NavRoutes.PARENT_CATEGORY_SCREEN,
        onBack = { showAlertDialogBox = true }
    )



}



@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewAreYouSureToCancelAlertBox() {
    AreYouSureToCancelAlertBox(
        modifier = Modifier,
        onDismissRequest = {},
        navController = NavController(LocalContext.current)
    )
}



@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_2")
@Composable
fun PreviewUserItemInputScreen() {
    UserItemInputScreen(
        modifier = Modifier,
        navController = NavController(LocalContext.current)
    )
}

