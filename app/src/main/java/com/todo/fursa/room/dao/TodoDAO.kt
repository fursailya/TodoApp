package com.todo.fursa.room.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.todo.fursa.room.model.Todo

@Dao
interface TodoDAO {

    @Query("SELECT * FROM todo")
    fun getAll(): LiveData<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(todo: Todo?)

    @Query("DELETE FROM todo WHERE id=:todoId")
    fun deleteById(todoId: Long)

    @Query("DELETE FROM todo")
    fun deleteAll()

    @Query("SELECT * FROM todo WHERE id=:todoId")
    fun selectById(todoId: Long): LiveData<Todo>

    @Query("SELECT COUNT(id) FROM todo")
    fun size(): LiveData<Int>
}