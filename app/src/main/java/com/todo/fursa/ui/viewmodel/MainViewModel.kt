package com.todo.fursa.ui.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.todo.fursa.room.model.Todo
import com.todo.fursa.room.repository.MainRepository

open class MainViewModel : AndroidViewModel {
    private var mainRepository: MainRepository
    private var mAllNotes: LiveData<List<Todo>>

    constructor(application: Application) : super(application) {
        mainRepository = MainRepository(application)
        mAllNotes = mainRepository.getAllNotes()
    }

    fun getAll() = mAllNotes

    fun insert(todo: Todo) { mainRepository.save(todo) }

}