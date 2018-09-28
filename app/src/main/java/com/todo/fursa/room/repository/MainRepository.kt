package com.todo.fursa.room.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import com.todo.fursa.room.dao.TodoDAO
import com.todo.fursa.room.database.AppDatabase
import com.todo.fursa.room.model.Todo
import com.todo.fursa.ui.viewmodel.InsertAsyncTask

class MainRepository {
    private var db: AppDatabase
    private var dao: TodoDAO
    private var mAllNotes: LiveData<List<Todo>>

    constructor(application: Application) {
       db = AppDatabase.newInstance(application)!!
       dao = db.todoDAO
       mAllNotes = dao.getAll()
    }

    fun getAllNotes(): LiveData<List<Todo>> = mAllNotes

    fun save(todo: Todo) {
        InsertAsyncTask(dao).execute(todo)
    }

    fun clearAll() { dao.deleteAll() }

}