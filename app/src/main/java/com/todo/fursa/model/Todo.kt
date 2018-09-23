package com.todo.fursa.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo(

        /**
         * @author Fursa Ilya
         * @param id записи автоинкремент
         * @param title заголовок записи
         * @param description детали
         * @param time время напоминания(отпечаток)
         */

        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = true)
        val id: Int,

        @ColumnInfo(name = "title")
        val title: String,

        @ColumnInfo(name = "description")
        val description: String,

        @ColumnInfo(name = "time")
        val time: Long
)