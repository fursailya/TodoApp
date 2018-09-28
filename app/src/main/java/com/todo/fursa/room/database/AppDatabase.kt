package com.todo.fursa.room.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

import com.todo.fursa.room.dao.TodoDAO
import com.todo.fursa.room.model.Todo

@Database(entities = [Todo::class], version = 3)
abstract class AppDatabase : RoomDatabase() {

    abstract val todoDAO: TodoDAO

    companion object {
        private const val DATABASE_NAME = "todo_db"
        private var INSTANCE: AppDatabase? = null

        fun newInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AppDatabase::class.java, DATABASE_NAME)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }

            return INSTANCE
        }
    }
}
