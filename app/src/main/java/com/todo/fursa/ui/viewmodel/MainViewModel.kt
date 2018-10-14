package com.todo.fursa.ui.viewmodel

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.appcompat.app.AppCompatActivity
import com.todo.fursa.TodoApplication
import com.todo.fursa.room.model.Todo
import com.todo.fursa.room.repository.MainRepository
import javax.inject.Inject

class MainViewModel : AndroidViewModel {
    @Inject
    lateinit var mainRepository: MainRepository

    private var mAllNotes: LiveData<List<Todo>>

    constructor(application: Application) : super(application) {
        TodoApplication.component.inject(this)
        mAllNotes = mainRepository.getAllNotes()
    }

    fun getAll() = mAllNotes

    fun insert(todo: Todo) { mainRepository.save(todo) }

    fun clearAll() { mainRepository.clearAll() }

    fun selectById(id: Long): LiveData<Todo> { return mainRepository.selectById(id) }

    fun deleteById(id: Long) { mainRepository.deleteById(id) }

}