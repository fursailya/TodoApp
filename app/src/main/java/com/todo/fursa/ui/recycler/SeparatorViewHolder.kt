package com.todo.fursa.ui.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.todo.fursa.R
import kotlinx.android.synthetic.main.separator_item_row.view.*

class SeparatorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.title)
}