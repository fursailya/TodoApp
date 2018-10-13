package com.todo.fursa.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.todo.fursa.R
import com.todo.fursa.databinding.ActivityDetailBinding
import com.todo.fursa.ui.viewmodel.MainViewModel

const val LOG_TAG = "Todo/DetailActivity"

class DetailActivity : AppCompatActivity() {
    private lateinit var detailBinding: ActivityDetailBinding
    private lateinit var viewModel: MainViewModel
    private var id: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        id = intent.getLongExtra(MainActivity.EXTRA_NOTE_ID, 0)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.selectById(id).observe(this, Observer {
            detailBinding.todo = it
        })
    }
}
