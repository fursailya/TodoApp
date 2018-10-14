package com.todo.fursa.room.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

import com.todo.fursa.room.dao.TodoDAO
import com.todo.fursa.room.model.Todo

@Database(entities = [Todo::class], version = 6, exportSchema = false)
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
