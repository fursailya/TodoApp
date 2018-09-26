package com.todo.fursa.ui.recycler

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.todo.fursa.room.model.Todo

abstract class BaseListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), IBaseListAdapter<IBaseListItem>{
    protected val items: ArrayList<IBaseListItem> = ArrayList()

    override fun getItemCount(): Int = items.size
    override fun getItemViewType(position: Int) = items[position].getLayoutId()

    protected fun inflateByViewType(context: Context?, viewType: Int, parent: ViewGroup): View
            = LayoutInflater.from(context).inflate(viewType, parent, false)

    override fun add(newItem: IBaseListItem) {
        items.add(newItem)
        notifyDataSetChanged()
    }

    fun addAll(newItems: List<Todo>?) {

        items.clear()

        for (newItem in newItems ?: return) {
            items.add(newItem)
            notifyDataSetChanged()
        }

    }

    override fun addAtPosition(pos: Int, newItem: IBaseListItem) {
        items.add(pos, newItem)
        notifyDataSetChanged()
    }

    override fun clearAll() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun remove(pos: Int) {
        items.removeAt(pos)
        notifyDataSetChanged()
    }
}