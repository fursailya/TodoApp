package com.todo.fursa.ui.recycler.adapter.base

interface IBaseListAdapter<T> {

    fun add(newItem: T)

  //  fun addAll(newItems: ArrayList<T>?)

    fun addAtPosition(pos: Int, newItem: T)

    fun remove(pos: Int)

    fun clearAll()
}