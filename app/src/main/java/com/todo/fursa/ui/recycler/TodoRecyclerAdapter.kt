package com.todo.fursa.ui.recycler

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.todo.fursa.R
import com.todo.fursa.room.model.Todo
import java.lang.IllegalStateException

class TodoRecyclerAdapter: BaseListAdapter() {
    override fun addAll(newItems: ArrayList<IBaseListItem>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context

        return when(viewType) {
            R.layout.todo_item_row -> TodoViewHolder(inflateByViewType(context, viewType, parent))
            else -> throw IllegalStateException("There's no match with current layoutId")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is TodoViewHolder -> {
                val item = items[position] as Todo
                holder.title.text = item.title
                holder.description.text = item.text
            }

            else -> throw IllegalStateException("There's no match with current holder instance")
        }
    }
}