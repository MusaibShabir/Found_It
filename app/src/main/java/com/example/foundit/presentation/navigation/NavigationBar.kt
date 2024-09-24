package com.example.foundit.presentation.navigation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.foundit.presentation.data.navigation.NavigationItems
import com.example.foundit.presentation.navigation.components.NavigationIcon
import com.example.foundit.ui.theme.SelectedIconColor


@Composable
fun NavigationBar(modifier: Modifier, navController: NavHostController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val selectedItemIndex by remember(currentRoute) {
        derivedStateOf {
            NavigationItems.indexOfFirst { it.title == currentRoute }
        }
    }
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        androidx.compose.material3.NavigationBar(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
            tonalElevation = 12.dp
        ){
            NavigationItems.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = selectedItemIndex == index,
                    onClick = {
                        navController.navigate(item.title) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    colors = NavigationBarItemColors(
                        selectedIndicatorColor = SelectedIconColor,
                        selectedIconColor = Color.Black,
                        selectedTextColor = Color.Black,
                        disabledIconColor = Color.Transparent,
                        disabledTextColor = Color.Transparent,
                        unselectedIconColor = Color.Black,
                        unselectedTextColor = Color.Black
                    ),
                    label = {
                        Text(text = item.title)
                    },
                    alwaysShowLabel = true,
                    icon = {
                        if (index == selectedItemIndex) {
                            NavigationIcon(iconResource = item.selectedIcon)
                        } else {
                            NavigationIcon(iconResource = item.unselectedIcon)

                        }
                    }
                )
            }
        }
    }
}





@Composable
@Preview(showBackground = true, showSystemUi = false)
fun PreviewMainScreen() {
    NavigationBar(
        modifier = Modifier,
        navController = NavHostController(LocalContext.current)
    )
}

