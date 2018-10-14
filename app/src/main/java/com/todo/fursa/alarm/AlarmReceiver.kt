package com.todo.fursa.alarm

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import android.util.Log
import com.todo.fursa.TodoApplication
import com.todo.fursa.ui.activity.MainActivity
import com.todo.fursa.ui.fragment.AddTodoBottomSheet
import javax.inject.Inject

class AlarmReceiver: BroadcastReceiver() {
    @Inject
    lateinit var notificationHelper: NotificationHelper

    private lateinit var resultIntent: Intent
    private lateinit var pendingIntent: PendingIntent

    private lateinit var title: String
    private lateinit var text: String

    init {
        TodoApplication.component.inject(this)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {

        title = intent!!.extras.getString(AddTodoBottomSheet.TODO_TITLE, TITLE_NULL)
        text = intent!!.extras.getString(AddTodoBottomSheet.TODO_TEXT, TEXT_NULL)

        Log.d(LOG_TAG, "onReceive()")
        Log.d(LOG_TAG, "Title: $title")
        Log.d(LOG_TAG, "Text: $text")

        resultIntent = Intent(context, MainActivity::class.java)
        pendingIntent = PendingIntent.getActivity(context!!.applicationContext, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationHelper.getNotification(title, text, context.applicationContext).setContentIntent(pendingIntent)
        notificationHelper.notificationManager.notify(0, builder.build())
    }

    companion object {
        const val LOG_TAG = "Todo/AlarmReceiver"
        const val TITLE_NULL = "title_null"
        const val TEXT_NULL = "text_null"
    }
}