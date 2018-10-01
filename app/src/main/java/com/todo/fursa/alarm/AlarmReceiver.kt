package com.todo.fursa.alarm

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import com.todo.fursa.ui.activity.MainActivity
import com.todo.fursa.ui.fragment.AddTodoBottomSheet

class AlarmReceiver: BroadcastReceiver() {
    private lateinit var notificationHelper: NotificationHelper
    private lateinit var resultIntent: Intent
    private lateinit var pendingIntent: PendingIntent

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(LOG_TAG, "onReceive()")
        Log.d(LOG_TAG, "Title = ${intent!!.extras.getString(AddTodoBottomSheet.TODO_TITLE)}")
        Log.d(LOG_TAG, "Text = ${intent!!.extras.getString(AddTodoBottomSheet.TODO_TEXT
        )}")

        resultIntent = Intent(context, MainActivity::class.java)
        pendingIntent = PendingIntent.getActivity(context!!.applicationContext, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        notificationHelper = NotificationHelper(context!!.applicationContext)

        var builder = NotificationHelper.getNotification(
                intent!!.extras.getString(AddTodoBottomSheet.TODO_TITLE),
                intent!!.extras.getString(AddTodoBottomSheet.TODO_TEXT),
                context!!.applicationContext)
                .setContentIntent(pendingIntent)
                .build()

        notificationHelper.notificationManager.notify(0, builder)
    }

    companion object {
        const val LOG_TAG = "Todo/AlarmReceiver"
    }
}