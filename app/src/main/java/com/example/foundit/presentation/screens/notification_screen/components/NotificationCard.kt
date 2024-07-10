package com.example.foundit.presentation.screens.notification_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun NotificationItem(notification: Notification) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        shape = RoundedCornerShape(0.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notification Icon",
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.LightGray, shape = CircleShape)
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = notification.title,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = notification.msg,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}



///////////////////////////

@Composable
fun NotificationColumn(notifications: List<Notification>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
    ) {
        items(notifications) { notification ->
            NotificationItem(notification = notification)
        }
    }
}




////////////////////////

@Composable
fun NotificationCard() {
    val notifications = remember {
        listOf(
            Notification(1, "title", "Check its live movement now."),
            Notification(2, "title", "Check its live movement now."),
            Notification(3, "title", "Check its live movement now."),
            Notification(4, "title", "Check its live movement now."),
            Notification(5, "title", "Check its live movement now."),
            Notification(6, "title", "Check its live movement now."),
            Notification(7, "title", "Check its live movement now."),
            Notification(8, "title", "Check its live movement now."),
            Notification(9, "title", "Check its live movement now."),
            Notification(10, "title", "Check its live movement now."),
            Notification(11, "title", "Check its live movement now."),
            Notification(12, "title", "Check its live movement now."),
            Notification(13, "title", "Check its live movement now."),
            Notification(14, "title", "Check its live movement now."),
            Notification(15, "title", "Check its live movement now."),
            Notification(16, "title", "Check its live movement now."),
            Notification(17, "title", "Check its live movement now."),
            Notification(18, "title", "Check its live movement now."),
            Notification(19, "title", "Check its live movement now."),
            Notification(20, "title", "Check its live movement now.")
        )
    }

    NotificationColumn(notifications = notifications)
}



@Preview(showBackground = true,showSystemUi = true)
@Composable
fun PreviewNotificationApp() {
    NotificationCard()
}



