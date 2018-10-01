package com.todo.fursa.ui.recycler

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import com.todo.fursa.R
import com.todo.fursa.room.model.Separator
import com.todo.fursa.room.model.Todo
import com.todo.fursa.ui.activity.MainActivity
import com.todo.fursa.util.IListCallback
import com.todo.fursa.util.fromTimestamp
import com.todo.fursa.util.toast
import java.lang.IllegalStateException

class TodoRecyclerAdapter(private var callback: IListCallback): BaseListAdapter() {
    private var listCallback: IListCallback = callback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context

        return when(viewType) {
            R.layout.todo_item_row -> TodoViewHolder(inflateByViewType(context, viewType, parent))
            R.layout.separator_item_row -> SeparatorViewHolder(inflateByViewType(context, viewType, parent))
            else -> throw IllegalStateException("There's no match with current layoutId")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is TodoViewHolder -> {
                val item = items[position] as Todo
                holder.title.text = item.title
                holder.description.text = item.text
                holder.remindTime.text = fromTimestamp(item.time)
                when(item.priority) {
                    0 -> holder.priority.setBackgroundResource(R.drawable.priority_low)
                    1 -> holder.priority.setBackgroundResource(R.drawable.priority_high)
                }

                holder.itemView.setOnClickListener {
                    callback.onShowTodoDetails(item.id)
                }

            }

            is SeparatorViewHolder -> {
                val item = items[position] as Separator
                holder.title.text = item.title
            }

            else -> throw IllegalStateException("There's no match with current holder instance")
        }
    }

    companion object {
        const val LOG_TAG = "Todo/TodoAdapter"
    }
}