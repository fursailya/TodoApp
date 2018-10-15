package com.todo.fursa.room.repository

import androidx.lifecycle.LiveData
import com.todo.fursa.TodoApplication
import com.todo.fursa.room.dao.TodoDAO
import com.todo.fursa.room.model.Todo
import com.todo.fursa.ui.activity.MainActivity
import com.todo.fursa.ui.viewmodel.InsertAsyncTask
import javax.inject.Inject

class MainRepository {

    @Inject
    lateinit var dao: TodoDAO

    private var mAllNotes: LiveData<List<Todo>>

    init {
        TodoApplication.component.inject(this)
        mAllNotes = dao.getAll()
    }

    fun getAllNotes(): LiveData<List<Todo>> = mAllNotes

    fun save(todo: Todo) { InsertAsyncTask(dao).execute(todo) }

    fun clearAll() { dao.deleteAll() }

    fun deleteById(id: Long) { dao.deleteById(id) }

    fun selectById(id: Long): LiveData<Todo> { return dao.selectById(id) }

    fun size(): LiveData<Int> = dao.size()

}