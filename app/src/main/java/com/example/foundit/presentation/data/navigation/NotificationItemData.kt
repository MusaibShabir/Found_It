package com.example.foundit.presentation.data.navigation

data class NotificationItemData(
    val id: Int,
    val title: String,
    val message: String
)

// Sample list of notifications
val sampleNotifications = listOf(
    NotificationItemData(1, "New Message", "You have a new message from John."),
    NotificationItemData(2, "OrderShipped", "Your order #1234 has been shipped."),
    NotificationItemData(3, "Event Reminder", "Don't forget the meeting tomorrow at 10 AM. Testing new feature so that notification card expands."),
    NotificationItemData(4, "Account Update", "Your account password has been changed."),
    NotificationItemData(5, "Special Offer", "Get 20% off on your next purchase."),
    NotificationItemData(6, "Friend Request", "Jane Doe sent you a friend request."),
    NotificationItemData(7, "Software Update", "A new software update is available."),
    NotificationItemData(8, "Battery Low", "Your device battery is low."),
    NotificationItemData(9, "News Alert", "Breaking news: Important announcement."),
    NotificationItemData(10, "Weather Update", "Expect heavy rain in your area.")
)