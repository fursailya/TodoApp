package com.todo.fursa.room.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.todo.fursa.R
import com.todo.fursa.ui.recycler.IBaseListItem

/**
 * @author Fursa Ilya
 * email: fursa.ilya@gmail.com
 *
 * @param id записи автоинкремент
 * @param title заголовок записи
 * @param description детали
 * @param time время напоминания(отпечаток)
 */

@Entity(tableName = "todo")
data class Todo(
        @ColumnInfo(name = "title") var title: String,
        @ColumnInfo(name = "description") var text: String,
        @ColumnInfo(name = "time") var time: Long): IBaseListItem {

        override fun getLayoutId(): Int = R.layout.todo_item_row

        @PrimaryKey(autoGenerate = true) var id: Long = 0
}


