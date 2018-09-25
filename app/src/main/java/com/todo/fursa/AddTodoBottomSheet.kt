package com.todo.fursa

import android.os.Bundle
import android.support.design.button.MaterialButton
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

open class AddTodoBottomSheet: BottomSheetDialogFragment() {
    private lateinit var btnAdd: MaterialButton
    private lateinit var btnDismiss: MaterialButton
    private lateinit var rootView: View

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = layoutInflater.inflate(R.layout.bottom_sheet_add_todo, container, false)
        btnAdd = rootView.findViewById(R.id.btnAdd)
        btnDismiss = rootView.findViewById(R.id.btnDismiss)

        btnAdd.setOnClickListener {

        }

        btnDismiss.setOnClickListener { dismiss() }

        return rootView
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