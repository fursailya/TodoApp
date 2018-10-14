package com.todo.fursa.alarm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import com.todo.fursa.R
import com.todo.fursa.TodoApplication
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class NotificationHelper(context: Context): ContextWrapper(context)  {

    @Inject
    lateinit var notificationManager: NotificationManager

    init {

        TodoApplication.component.inject(this)

        val notificationChannel = NotificationChannel(FURSA_TODO_CHANNEL_ID, FURSA_TODO_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
        notificationChannel.enableLights(true)
        notificationChannel.enableVibration(true)
        notificationChannel.lightColor = Color.GREEN
        notificationChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 500, 200, 500)

        notificationManager.createNotificationChannel(notificationChannel)
    }


    companion object {
        const val FURSA_TODO_CHANNEL_ID = "fursa.ilya.todo.app"
        const val FURSA_TODO_CHANNEL_NAME = "notifications"

        fun getNotification(title: String, body: String, context: Context): Notification.Builder {
            return Notification.Builder(context.applicationContext, FURSA_TODO_CHANNEL_ID)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(true)
        }
    }
}