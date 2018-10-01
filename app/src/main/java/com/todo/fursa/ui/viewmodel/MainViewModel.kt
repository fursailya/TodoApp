package com.todo.fursa.ui.viewmodel

import android.app.Activity
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
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

    fun clearAll() { mainRepository.clearAll() }

    fun selectById(id: Long): LiveData<Todo> { return mainRepository.selectById(id) }

    companion object {
      //Todo Obtain View model method
    }

}