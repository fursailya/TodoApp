package com.todo.fursa.ui.recycler.holder

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.todo.fursa.R
import kotlinx.android.synthetic.main.separator_item_row.view.*

class SeparatorViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.title)
}