package com.todo.fursa.dagger.module

import android.app.AlarmManager
import android.app.NotificationManager
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import android.support.annotation.RequiresApi
import com.todo.fursa.alarm.NotificationHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ManageModule(context: Context) {
    private val appContext: Context = context

    @RequiresApi(Build.VERSION_CODES.O)
    @Singleton
    @Provides
    fun provideNotificationHelper() = NotificationHelper(context = appContext.applicationContext)

    @Singleton
    @Provides
    fun provideAlarmManager() = appContext.getSystemService(ALARM_SERVICE) as AlarmManager

    @Singleton
    @Provides
    fun provideNotificationManager() = appContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
}