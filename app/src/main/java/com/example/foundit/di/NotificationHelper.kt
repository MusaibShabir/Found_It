package com.example.foundit.di

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.foundit.MainActivity
import com.example.foundit.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationHelper @Inject constructor(@ApplicationContext private val context: Context) {

    private val notificationManager = NotificationManagerCompat.from(context)

    fun showNotification(title: String, content: String) {
        createNotificationChannel()

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.app_name_icon) // Replace with your icon
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // Important for heads-up
            .setFullScreenIntent(getFullScreenPendingIntent(), true) // Crucial for heads-up

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Handle this part
            return
        }
        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    //  (New) Create a PendingIntent for full-screen intent
    private fun getFullScreenPendingIntent(): PendingIntent {
        val fullScreenIntent = Intent(context, MainActivity::class.java) // Or your target activity
        return PendingIntent.getActivity(
            context,
            0,
            fullScreenIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT // Use FLAG_MUTABLE if targeting API level <= 30
        )
    }

    private fun createNotificationChannel() {
        val name = CHANNEL_NAME
        val descriptionText = CHANNEL_DESCRIPTION
        val importance = NotificationManager.IMPORTANCE_HIGH // Set to high importance
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
            enableLights(true) // Enable notification light
            enableVibration(true) // Enable vibration
            setShowBadge(true)
        }
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    companion object {
        private const val CHANNEL_ID = "my_channel_id"
        private const val CHANNEL_NAME = "My Channel"
        private const val CHANNEL_DESCRIPTION = "Channel Description"
        private const val NOTIFICATION_ID = 1001
    }
}
