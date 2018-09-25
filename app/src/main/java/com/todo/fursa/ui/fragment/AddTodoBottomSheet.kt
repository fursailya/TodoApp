package com.todo.fursa.ui.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.button.MaterialButton
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.TextInputEditText
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.todo.fursa.R
import com.todo.fursa.room.model.Todo
import com.todo.fursa.ui.viewmodel.MainViewModel

open class AddTodoBottomSheet: BottomSheetDialogFragment() {
    private lateinit var btnAdd: MaterialButton
    private lateinit var btnDismiss: MaterialButton
    private lateinit var rootView: View
    private lateinit var tilTitle: TextInputEditText
    private lateinit var tilText: TextInputEditText

    private lateinit var viewModel: MainViewModel

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this@AddTodoBottomSheet).get(MainViewModel::class.java)

        rootView = layoutInflater.inflate(R.layout.bottom_sheet_add_todo, container, false)

        btnAdd = rootView.findViewById(R.id.btnAdd)
        btnDismiss = rootView.findViewById(R.id.btnDismiss)

        tilTitle = rootView.findViewById(R.id.tilTitle)
        tilText = rootView.findViewById(R.id.tilText)

        btnAdd.setOnClickListener {
            viewModel.insert(Todo(tilTitle.text.toString(), tilText.text.toString(), System.currentTimeMillis()))
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