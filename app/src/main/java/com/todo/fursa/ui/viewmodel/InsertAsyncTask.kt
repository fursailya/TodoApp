package com.todo.fursa.ui.viewmodel

import android.os.AsyncTask
import com.todo.fursa.room.dao.TodoDAO
import com.todo.fursa.room.model.Todo

class InsertAsyncTask: AsyncTask<Todo, Void, Void> {
    private var todoDAO: TodoDAO

    constructor(dao: TodoDAO) {
        todoDAO = dao
    }


    override fun doInBackground(vararg params: Todo?): Void? {
        todoDAO.save(params[0])
        return null
    }
}