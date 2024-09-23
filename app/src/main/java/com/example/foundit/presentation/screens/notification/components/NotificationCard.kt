package com.example.foundit.presentation.screens.notification.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foundit.presentation.data.navigation.NotificationItemData
import com.example.foundit.presentation.screens.notification.NotificationBaseViewModel
import com.example.foundit.ui.theme.MainGreen


@Composable
fun NotificationItem(
    modifier: Modifier,
    notification: NotificationItemData,
    isExpanded: Boolean,
    onClick:()-> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Max)
            .padding(0.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MainGreen),
    ) {
        Row(modifier = modifier.padding(16.dp)) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notification Icon",
                modifier = modifier
                    .size(40.dp)
                    .background(Color.LightGray, shape = CircleShape)
                    .padding(8.dp)
            )
            Spacer(modifier = modifier.width(16.dp))
            Column {
                Text(
                    text = notification.title,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp),
                    color = Color.White
                )
                Spacer(modifier = modifier.height(4.dp))
                Text(
                    text = notification.message,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp),
                    color = Color.White,
                    maxLines = if(isExpanded) Int.MAX_VALUE else 1,//if isExpanded is true this expands the card
                    overflow =  TextOverflow.Ellipsis,//this adds ellipses at the end of a notification where multiline text is available
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewNotificationItem() {
    NotificationItem(
        modifier = Modifier,
        notification = NotificationItemData(
            id = 1,
            title = "This is a Notification Test",
            message = "This is a Notification Test"
        ),
        isExpanded = false,
        onClick = {}
    )
}



@Composable
fun NotificationColumn(
    modifier: Modifier,
    notifications: List<NotificationItemData>
) {
    val expandedStates = remember { mutableStateMapOf<NotificationItemData, Boolean>()}// to track the expansion
    if (notifications.isEmpty()) {
        Text(
            text = "No notification Available", //stringResource(id = R.string.when_no_notification),
            color = Color.White, //
            modifier = modifier.padding(16.dp)
        )
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(notifications) { notification ->
                val isExpanded = expandedStates[notification] ?: false
                NotificationItem(
                    modifier = modifier,
                    notification = notification,
                    isExpanded = isExpanded,
                    onClick = {
                        expandedStates[notification] = !isExpanded //changes between states i.e expanded or collapsed
                    })
                Spacer(modifier = modifier.height(16.dp)) // Add space between each card
            }
        }
    }
}


// View-Model Composable
@Composable
fun NotificationCard(
    modifier: Modifier,
    viewModel: NotificationBaseViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val notifications by viewModel.notifications.collectAsState()
    NotificationColumn(modifier = modifier, notifications = notifications)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewNotificationApp() {
    NotificationCard(modifier = Modifier)
}



