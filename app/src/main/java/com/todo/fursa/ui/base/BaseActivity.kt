package com.todo.fursa.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import com.google.android.material.bottomappbar.BottomAppBar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import com.todo.fursa.TodoApplication

private const val LOG_TAG = "Todo/BaseActivity"

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentViewLayout())
        TodoApplication.component.inject(this)
    }

    @LayoutRes
    protected abstract fun getContentViewLayout(): Int

    protected fun setUpBottomAppBar(appBar: BottomAppBar) {
        if(appBar != null) {
            setSupportActionBar(appBar)
        }
    }



}