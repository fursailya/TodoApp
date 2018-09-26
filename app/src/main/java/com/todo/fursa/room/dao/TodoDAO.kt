package com.todo.fursa.room.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.todo.fursa.room.model.Todo

/**
 *  @author Fursa Ilya
 *
 *  getAll - gets all items from the database
 *  save - saves item ot the database
 *  delete - gets id and remove an item by id
 *  selectById - is almost the same with delete, but It gets an item by it's id
 */

@Dao
interface TodoDAO {

    @Query("SELECT * FROM todo")
    fun getAll(): LiveData<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(todo: Todo?)

    @Query("DELETE FROM todo WHERE id=:todoId")
    fun delete(todoId: Int)

    @Query("SELECT * FROM todo WHERE id=:todoId")
    fun selectById(todoId: Int): LiveData<Todo>

    @Query("SELECT COUNT(id) FROM todo")
    fun size(): LiveData<Int>
}