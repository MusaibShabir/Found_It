package com.example.foundit.presentation.data.navigation

import com.example.foundit.R.drawable.filled_home
import com.example.foundit.R.drawable.filled_notifications
import com.example.foundit.R.drawable.filled_pending
import com.example.foundit.R.drawable.filled_person
import com.example.foundit.R.drawable.outline_home
import com.example.foundit.R.drawable.outline_notifications
import com.example.foundit.R.drawable.outline_pending
import com.example.foundit.R.drawable.outline_profile

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
        selectedIcon = filled_home,
        unselectedIcon =  outline_home,
        hasNews = false,
    ),
    BottomNavigationItem(
        title = NavRoutes.PROGRESS,
        selectedIcon =  filled_pending ,
        unselectedIcon = outline_pending,
        hasNews = true,
    ),
    BottomNavigationItem(
        title = NavRoutes.NOTIFICATIONS,
        selectedIcon =  filled_notifications,
        unselectedIcon =  outline_notifications,
        hasNews = true,
    ),
    BottomNavigationItem(
        title = NavRoutes.PROFILE,
        selectedIcon =  filled_person,
        unselectedIcon =  outline_profile,
        hasNews = true,
    ),
)

