package com.example.foundit.presentation.screens.input

import android.os.Build
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.foundit.presentation.screens.input.common.ColorCategoryScreen
import com.example.foundit.presentation.screens.input.common.MapScreen
import com.example.foundit.presentation.screens.input.common.ParentCategoryScreen
import com.example.foundit.presentation.screens.input.common.components.AreYouSureToCancelAlertBox
import com.example.foundit.presentation.screens.input.common.components.ItemDescriptionAndDatePickerScreen
import com.example.foundit.presentation.screens.input.common.components.UserInputBottomNavigationBar
import com.example.foundit.presentation.screens.input.lost.LostInputViewModel


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun UserItemInputScreen(
    modifier: Modifier,
    navController: NavController
) {
    val lostInputViewModel: LostInputViewModel = hiltViewModel()

    val cardType = navController.currentBackStackEntry?.arguments?.getInt("cardType")
    // Storing the Card Type in the ViewModel
    LaunchedEffect(cardType) {
        if (cardType != null) {
            lostInputViewModel.storeCardType(cardType)
        }
    }

    val context = LocalContext.current

    val isMapMarkerLocationEmpty by lostInputViewModel.markerPosition.collectAsState()
    val isParentSelectedCategoryEmpty by lostInputViewModel.parentSelectedCategoryId.collectAsState()
    val isColorSelectedCategoryEmpty by lostInputViewModel.colorSelectedId.collectAsState()
    val isChildSelectedCategoryEmpty by lostInputViewModel.selectedChildCategoryIds.collectAsState()
    val isDescriptionEmpty by lostInputViewModel.itemDescription.collectAsState()
    val isDateEmpty by lostInputViewModel.selectedDateMillis.collectAsState()

    // Minimum Character Length For Description
    val minCharLength = 25

    var showAlertDialogBox by remember { mutableStateOf(false) }
    val navControllerForUserInputScreen = rememberNavController()
    val currentRoute = currentRoute(navControllerForUserInputScreen)

    var topBarTitle by remember { mutableStateOf("") }

    when(cardType){
        0 -> topBarTitle = "Report Lost Item"
        1 -> topBarTitle = "Report Found Item"
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TheTopAppBar(title = topBarTitle, isNavigationIconVisible = false, navController = navController) },
        bottomBar = {
            UserInputBottomNavigationBar(
                modifier = modifier,

                cancelOrBackButtonText = when(currentRoute) {
                    NavRoutes.MAP_SCREEN -> { "Cancel" }
                    else -> { "Back" }
                },

                nextOrSubmitButtonText = when(currentRoute) {
                    NavRoutes.ITEM_DESCRIPTION_SCREEN -> { "Submit" }
                    else -> { "Next" }
                },

                onCancelOrBackClick = {
                    when(currentRoute) {
                        NavRoutes.MAP_SCREEN -> {
                            showAlertDialogBox = true
                        }
                        else -> { navControllerForUserInputScreen.popBackStack() }
                    }
                                      },
                nextButtonEnabled = {
                        when (currentRoute) {
                            NavRoutes.MAP_SCREEN -> isMapMarkerLocationEmpty !== null
                            NavRoutes.PARENT_CATEGORY_SCREEN -> isParentSelectedCategoryEmpty.isNotEmpty()
                            NavRoutes.COLOR_CATEGORY_SCREEN -> isColorSelectedCategoryEmpty.isNotEmpty()
                            NavRoutes.CHILD_CATEGORY_SCREEN -> isChildSelectedCategoryEmpty.isNotEmpty()
                            NavRoutes.ITEM_DESCRIPTION_SCREEN -> isDescriptionEmpty.length >= minCharLength && isDateEmpty !== null
                            else -> true
                        }

                },
                onNextClick = {
                    when (currentRoute) {
                    NavRoutes.MAP_SCREEN -> {
                        navControllerForUserInputScreen.navigate(NavRoutes.PARENT_CATEGORY_SCREEN)
                    }
                        NavRoutes.PARENT_CATEGORY_SCREEN -> {
                        navControllerForUserInputScreen.navigate(NavRoutes.COLOR_CATEGORY_SCREEN)
                    }
                    NavRoutes.COLOR_CATEGORY_SCREEN -> {
                        navControllerForUserInputScreen.navigate(NavRoutes.CHILD_CATEGORY_SCREEN)
                    }
                    NavRoutes.CHILD_CATEGORY_SCREEN -> {
                        navControllerForUserInputScreen.navigate(NavRoutes.ITEM_DESCRIPTION_SCREEN)
                    }

                        NavRoutes.ITEM_DESCRIPTION_SCREEN -> {
                            if(lostInputViewModel.isNetworkAvailableViewmodel(context)) {
                                lostInputViewModel.onSubmitClick { isSucess, _ ->
                                    if (isSucess) {
                                        Toast.makeText(
                                            context,
                                            "Your Item was Registered Succesfully",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        navController.navigate(NavRoutes.HOME)
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Something Went Wrong",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "Please Connect to the Internet",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                    }else -> {

                        }
                    }

                }
            )
        }

    ){ paddingValues ->
        NavHost(
            navController = navControllerForUserInputScreen,
            startDestination = NavRoutes.MAP_SCREEN,
            modifier = modifier.padding(paddingValues)
        ) {

            composable(NavRoutes.MAP_SCREEN) {
                MapScreen(
                    modifier = modifier,
                    viewModel = lostInputViewModel,
                    navController = navController
                )
            }

            composable(NavRoutes.PARENT_CATEGORY_SCREEN) {
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

            composable(NavRoutes.ITEM_DESCRIPTION_SCREEN) {
                ItemDescriptionAndDatePickerScreen(
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
        enabled = currentRoute == NavRoutes.MAP_SCREEN,
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



@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_2")
@Composable
fun PreviewUserItemInputScreen() {
    UserItemInputScreen(
        modifier = Modifier,
        navController = NavController(LocalContext.current)
    )
}

