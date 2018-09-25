package com.todo.fursa

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

open class AddTodoBottomSheet: BottomSheetDialogFragment() {

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.bottom_sheet_add_todo, container, false)
    }

    companion object {
        fun newInstance(): BottomSheetDialogFragment {
            val dialog = AddTodoBottomSheet()
            val args = Bundle()
            dialog.arguments = args
            return dialog
        }
    }
}