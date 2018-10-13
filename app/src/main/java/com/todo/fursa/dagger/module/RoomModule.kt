package com.todo.fursa.dagger.module

import android.content.Context
import com.todo.fursa.room.database.AppDatabase
import com.todo.fursa.room.repository.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(context: Context) {
    private val appContext: Context = context

    @Singleton
    @Provides
    fun provideAppDatabase() = AppDatabase.newInstance(context = appContext)

    @Singleton
    @Provides
    fun provideAppDatabaseDAO() = AppDatabase.newInstance(context = appContext)!!.todoDAO

    @Singleton
    @Provides
    fun provideAppRepository() = MainRepository()
}