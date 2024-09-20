package com.example.foundit.presentation.screens.input

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.example.foundit.presentation.screens.input.common.components.ColorCategoryScreen
import com.example.foundit.presentation.screens.input.common.components.ItemDescriptionScreen
import com.example.foundit.presentation.screens.input.common.components.UserInputBottomNavigationBar
import com.example.foundit.presentation.screens.input.found.FoundInputViewModel
import com.example.foundit.presentation.screens.input.lost.LostInputViewModel


@Composable
fun UserItemInputScreen(
    modifier: Modifier,
    navController: NavController
) {
    val lostInputViewModel: LostInputViewModel = hiltViewModel()
    val foundInputViewModel: FoundInputViewModel= hiltViewModel()


    val isChildSelectedCategoryEmpty by lostInputViewModel.selectedChildCategoryIds.collectAsState()
    val isDescriptionEmpty by lostInputViewModel.itemDescription.collectAsState()

    // Minimum Character Length For Description
    val minCharLength = 25

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

                nextOrSubmitButtonText = when(currentRoute) {
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
                nextButtonEnabled = { when (currentRoute) {
                    NavRoutes.PARENT_CATEGORY_SCREEN -> true
                    NavRoutes.COLOR_CATEGORY_SCREEN -> true
                    NavRoutes.CHILD_CATEGORY_SCREEN -> isChildSelectedCategoryEmpty.isNotEmpty()
                    NavRoutes.ITEM_DESCRIPTION_SCREEN -> isDescriptionEmpty.length >= minCharLength
                    else -> true
                }
                },
                        // here
                onNextClick = {
                    when (currentRoute) {
                    NavRoutes.PARENT_CATEGORY_SCREEN -> {
                        navControllerForUserInputScreen.navigate(NavRoutes.COLOR_CATEGORY_SCREEN)
                    }
                    NavRoutes.COLOR_CATEGORY_SCREEN -> {
                        navControllerForUserInputScreen.navigate(NavRoutes.CHILD_CATEGORY_SCREEN)
                    }
                    NavRoutes.CHILD_CATEGORY_SCREEN -> {
                        navControllerForUserInputScreen.navigate(NavRoutes.ITEM_DESCRIPTION_SCREEN)
                    }
                        NavRoutes.ITEM_DESCRIPTION_SCREEN -> { lostInputViewModel.onSubmitClick()
                    }else -> {

                        }
                    }

                }
            )
        }

    ){ paddingValues ->
        NavHost(
            navController = navControllerForUserInputScreen,
            startDestination = NavRoutes.PARENT_CATEGORY_SCREEN,
            modifier = modifier.padding(paddingValues)
        ) {

            composable(NavRoutes.PARENT_CATEGORY_SCREEN,
            ) {
                ParentCategoryScreen(
                    modifier = modifier,
                    viewModel = lostInputViewModel
                )
            }

            composable(
                NavRoutes.COLOR_CATEGORY_SCREEN,
            ) {
                ColorCategoryScreen(
                    modifier = modifier,
                    viewModel = lostInputViewModel
                )
            }

            composable(NavRoutes.CHILD_CATEGORY_SCREEN) {
                ChildCategoryScreen(
                    modifier = modifier,
                    viewModel = lostInputViewModel
                )
            }

            composable(NavRoutes.CHILD_CATEGORY_SCREEN2) {
                ChildCategoryScreen2(modifier = modifier)
            }

            composable(NavRoutes.ITEM_DESCRIPTION_SCREEN) {
                ItemDescriptionScreen(
                    modifier = modifier,
                    viewModel = lostInputViewModel
                )
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

