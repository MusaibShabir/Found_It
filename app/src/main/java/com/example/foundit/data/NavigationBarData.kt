package com.example.foundit.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.foundit.R

object NavRoutes {
    const val HOME = "Home"
    const val PROGRESS = "Progress"
    const val NOTIFICATIONS = "Notifications"
    const val PROFILE = "Profile"
}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: @Composable () -> Unit,
    val unselectedIcon: @Composable () -> Unit,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

val NavigationItems = listOf(
    BottomNavigationItem(
        title = NavRoutes.HOME,
        selectedIcon = { NavigationIcon(painterResource(id = R.drawable.filled_home)) },
        unselectedIcon = { NavigationIcon(painterResource(id = R.drawable.outline_home)) },
        hasNews = false,
    ),
    BottomNavigationItem(
        title = NavRoutes.PROGRESS,
        selectedIcon = { NavigationIcon(painterResource(id = R.drawable.outline_progress_activity)) },
        unselectedIcon ={ NavigationIcon(painterResource(id = R.drawable.outline_progress_activity)) },
        hasNews = false,
        badgeCount = 3
    ),
    BottomNavigationItem(
        title = NavRoutes.NOTIFICATIONS,
        selectedIcon = { NavigationIcon(painterResource(id = R.drawable.filled_notifications)) },
        unselectedIcon = { NavigationIcon(painterResource(id = R.drawable.outline_notifications)) },
        hasNews = true,
    ),
    BottomNavigationItem(
        title = NavRoutes.PROFILE,
        selectedIcon = { NavigationIcon(painterResource(id = R.drawable.filled_person)) },
        unselectedIcon = { NavigationIcon(painterResource(id = R.drawable.outline_profile)) },
        hasNews = true,
    ),
)

