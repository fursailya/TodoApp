package com.todo.fursa.room.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.todo.fursa.R
import com.todo.fursa.ui.recycler.adapter.base.IBaseListItem

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
        @ColumnInfo(name = "priority") val priority: Int,
        @ColumnInfo(name = "time") var time: Long): IBaseListItem, Comparable<Todo>, Parcelable {

        override fun compareTo(other: Todo): Int {
                return Integer.compare(other.priority, this.priority)
        }

        override fun getLayoutId(): Int = R.layout.todo_item_row

        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = true) var id: Long = 0

        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readInt(),
                parcel.readLong()) {
                id = parcel.readLong()
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(title)
                parcel.writeString(text)
                parcel.writeInt(priority)
                parcel.writeLong(time)
                parcel.writeLong(id)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Todo> {
                override fun createFromParcel(parcel: Parcel): Todo {
                        return Todo(parcel)
                }

                override fun newArray(size: Int): Array<Todo?> {
                        return arrayOfNulls(size)
                }
        }
}


