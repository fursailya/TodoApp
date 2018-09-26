package com.todo.fursa.ui.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.todo.fursa.R

class TodoViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
    override fun onClick(v: View?) {
       Toast.makeText(v!!.context, "Title = $title", Toast.LENGTH_LONG).show()
    }

    val title: TextView = view.findViewById(R.id.titleTextView)
    val description: TextView = view.findViewById(R.id.textTextView)
}