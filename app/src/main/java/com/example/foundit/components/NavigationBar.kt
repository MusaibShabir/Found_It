package com.example.foundit.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.foundit.data.NavigationItems
import com.example.foundit.ui.theme.SelectedIcon


@Composable
fun NavigationBar(modifier: Modifier, navController: NavHostController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    LaunchedEffect(currentRoute) {
        selectedItemIndex = NavigationItems.indexOfFirst { it.title == currentRoute }
    }

    Card(
        shape = RoundedCornerShape(42.dp),
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            androidx.compose.material3.NavigationBar(
                containerColor = Color.White,
            ) {
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
                            selectedIndicatorColor = SelectedIcon,
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
                            BadgedBox(
                                badge = {
                                    if (item.badgeCount != null) {
                                        Badge {
                                            Text(text = item.badgeCount.toString())
                                        }
                                    } else if (item.hasNews) {
                                        Badge()
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}
/*
@Composable
@Preview(showBackground = true, showSystemUi = false)
fun PreviewMainScreen() {
    NavigationBar(modifier = Modifier)
}
 */
