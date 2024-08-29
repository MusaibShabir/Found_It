package com.example.foundit.presentation.screens.notification.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foundit.presentation.data.navigation.NotificationItemData
import com.example.foundit.presentation.screens.notification.NotificationBaseViewModel
import com.example.foundit.ui.theme.MainGreen

@Composable
fun NotificationItem(notification: NotificationItemData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        shape = RoundedCornerShape(35.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MainGreen),
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
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp),
                    color = Color.White // Change text color to white
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = notification.msg,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp),
                    color = Color.White // Change text color to white
                )
            }
        }
    }
}

///////////////////////////

@Composable
fun NotificationColumn(notifications: List<NotificationItemData>) {
    if (notifications.isEmpty()) {
        Text(
            text = "No notifications available.",
            color = Color.White, // Optional: Change this text color to white as well
            modifier = Modifier.padding(16.dp)
        )
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp) // Added padding around the LazyColumn
        ) {
            items(notifications) { notification ->
                NotificationItem(notification = notification)
                Spacer(modifier = Modifier.height(16.dp)) // Add space between each card
            }
        }
    }
}

////////////////////////

@Composable
fun NotificationCard(
    modifier: Modifier,
    viewModel: NotificationBaseViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val notifications by viewModel.notifications.collectAsState()
    NotificationColumn(notifications = notifications)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewNotificationApp() {
    NotificationCard(modifier = Modifier)
}
