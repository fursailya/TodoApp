package com.todo.fursa.room.model

import com.todo.fursa.R
import com.todo.fursa.ui.recycler.IBaseListItem

data class Separator(val title: String): IBaseListItem {

    override fun getLayoutId(): Int = R.layout.separator_item_row
}