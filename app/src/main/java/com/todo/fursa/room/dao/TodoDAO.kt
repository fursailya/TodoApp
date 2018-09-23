package com.todo.fursa.room.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.todo.fursa.room.model.Todo

/**
 *  @author Fursa Ilya
 */

@Dao
interface TodoDAO {

    @Query("SELECT * FROM todo")
    fun getAll(): LiveData<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(todo: Todo)

    @Query("DELETE FROM todo WHERE id=:todoId")
    fun delete(todoId: Int)
}