package com.example.foundit.presentation.screens.input.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foundit.presentation.common.TheTopAppBar
import com.example.foundit.presentation.screens.input.data.parentCategories


@Composable
fun UserItemInputScreen(
    modifier: Modifier,
    navController: NavController
) {
    var showAlertDialogBox by remember { mutableStateOf(false) } // State for the alert dialog

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TheTopAppBar(title = "Report Lost Item", navController = navController) },
        bottomBar = {
            UserInputBottomNavigationBar(
                modifier = modifier,
                onCancelOrBackClick = { showAlertDialogBox = true },
                onNextClick = { /*TODO*/ })
        }

    ){ paddingValues ->
        LazyVerticalGrid(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Bottom,
            columns = GridCells.Adaptive(minSize = 110.dp)
        ) {
            items(parentCategories) { parentCategory ->
                CategoryCard(
                    modifier = Modifier,
                    categoryText = parentCategory.name,
                    onCategoryClick = { }
                )
            }
        }

    }

    if (showAlertDialogBox) {
        AreYouSureToCancelAlertBox(
            modifier = modifier,
            onDismissRequest = { showAlertDialogBox = false },
            navController = navController
        )
    }
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
fun PreviewParentCategoryScreen() {
    UserItemInputScreen(
        modifier = Modifier,
        navController = NavController(LocalContext.current)
    )
}

