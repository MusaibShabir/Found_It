package com.example.foundit.presentation.data.navigation

import com.example.foundit.R

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

