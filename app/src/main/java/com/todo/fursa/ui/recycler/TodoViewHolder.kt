package com.todo.fursa.ui.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.todo.fursa.R

class TodoViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val title: TextView = view.findViewById(R.id.titleTextView)
    val description: TextView = view.findViewById(R.id.textTextView)
    val priority: ImageView = view.findViewById(R.id.priorityImageView)
    val remindTime: TextView = view.findViewById(R.id.remindTimeTextView)
}