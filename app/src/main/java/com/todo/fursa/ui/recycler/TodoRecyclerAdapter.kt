package com.todo.fursa.ui.recycler

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import com.todo.fursa.R
import com.todo.fursa.R.drawable.priority_high
import com.todo.fursa.room.model.Todo
import java.lang.IllegalStateException

class TodoRecyclerAdapter: BaseListAdapter() {

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

                when(item.priority) {
                    0 -> holder.priority.setBackgroundResource(R.drawable.priority_low)
                    1 -> holder.priority.setBackgroundResource(R.drawable.priority_high)
                }
            }

            else -> throw IllegalStateException("There's no match with current holder instance")
        }
    }

    companion object {
        const val LOG_TAG = "Todo/TodoAdapter"
    }
}