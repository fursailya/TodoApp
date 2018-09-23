package com.todo.fursa.room.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

/**
 * @author Fursa Ilya
 * email: fursa.ilya@gmail.com
 * @param id записи автоинкремент
 * @param title заголовок записи
 * @param description детали
 * @param time время напоминания(отпечаток)
 */

@Entity(tableName = "todo")
class Todo(
        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = true)
        val id: Int,

        @ColumnInfo(name = "title")
        val title: String,

        @ColumnInfo(name = "description")
        val description: String,

        @ColumnInfo(name = "time")
        val time: Long
): Comparable<Todo>, Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString(),
                parcel.readString(),
                parcel.readLong())

        override fun writeToParcel(dest: Parcel?, flags: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun describeContents(): Int {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun compareTo(other: Todo): Int {
                return this.time.compareTo(other.time)
        }

        override fun toString(): String {
                return "\nTodo(id=$id, title='$title', description='$description', time=$time)"
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