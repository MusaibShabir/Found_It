package com.example.foundit.presentation.screens.notification

import com.example.foundit.presentation.MainViewModel
import com.example.foundit.presentation.data.navigation.sampleNotifications
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NotificationBaseViewModel: MainViewModel() {
    private val _notifications = MutableStateFlow(sampleNotifications)
    val notifications = _notifications.asStateFlow()

    /*
    to Be used for Dynamic Notifications
    init {
        viewModelScope.launch {
            _notifications.value = fetchNotifications()
        }
    }


    private suspend fun fetchNotifications(): List<NotificationItemData> {return try {
        val notificationDataFromRepo = notificationRepository.getNotifications()
        notificationDataFromRepo.map { rawData ->
            NotificationItemData(
                id = rawData.id, // Adjust based on your raw data structure
                title = rawData.title, // Adjust based on your raw data structure
                msg = rawData.message // Adjust based on your raw data structure
            )
        }
    } catch (e: Exception) {
        // Handle errors (e.g., network errors, database errors)
        emptyList() // Return an empty list or handle the error appropriately
    }

     */

}