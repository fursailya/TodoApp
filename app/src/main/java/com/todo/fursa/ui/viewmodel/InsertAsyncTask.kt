package com.todo.fursa.ui.viewmodel

import android.os.AsyncTask
import com.todo.fursa.TodoApplication
import com.todo.fursa.room.dao.TodoDAO
import com.todo.fursa.room.model.Todo
import javax.inject.Inject

class InsertAsyncTask: AsyncTask<Todo, Void, Void> {
    @Inject
    lateinit var dao: TodoDAO

    constructor(dao: TodoDAO) {
        TodoApplication.component.inject(this)
        this.dao = dao
    }


    override fun doInBackground(vararg params: Todo?): Void? {
        dao.save(params[0])
        return null
    }
}


