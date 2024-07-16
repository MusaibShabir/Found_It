package com.example.foundit.presentation.data

import com.example.foundit.R

object NavRoutes {
    const val HOME = "Home"
    const val PROGRESS = "Progress"
    const val NOTIFICATIONS = "Notifications"
    const val PROFILE = "Profile"
}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

val NavigationItems = listOf(
    BottomNavigationItem(
        title = NavRoutes.HOME,
        selectedIcon = R.drawable.filled_home,
        unselectedIcon =  R.drawable.outline_home,
        hasNews = false,
    ),
    BottomNavigationItem(
        title = NavRoutes.PROGRESS,
        selectedIcon =  R.drawable.outline_progress_activity,
        unselectedIcon = R.drawable.outline_progress_activity,
        hasNews = true,
    ),
    BottomNavigationItem(
        title = NavRoutes.NOTIFICATIONS,
        selectedIcon =  R.drawable.filled_notifications,
        unselectedIcon =  R.drawable.outline_notifications,
        hasNews = true,
    ),
    BottomNavigationItem(
        title = NavRoutes.PROFILE,
        selectedIcon =  R.drawable.filled_person,
        unselectedIcon =  R.drawable.outline_profile,
        hasNews = true,
    ),
)

