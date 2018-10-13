package com.todo.fursa.dagger.module

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

@Module
class AppModule(context: Context) {
    private val appContext: Context = context

    @Singleton
    @Provides
    fun provideAppContext() = appContext

    @Singleton
    @Provides
    fun provideCalendar() = Calendar.getInstance()!!

    @Singleton
    @Provides
    fun provideFirebaseInstance() = FirebaseAuth.getInstance()!!

}