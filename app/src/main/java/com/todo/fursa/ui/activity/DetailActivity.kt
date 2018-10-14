package com.todo.fursa.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.todo.fursa.R
import com.todo.fursa.databinding.ActivityDetailBinding
import com.todo.fursa.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_detail.*

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

        buttonDelete.setOnClickListener {
            viewModel.deleteById(id)
            startMainActivity()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startMainActivity()
    }

    private fun startMainActivity() {
        val mainActivityIntent = Intent(this@DetailActivity, MainActivity::class.java)
        mainActivityIntent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(mainActivityIntent)
        this.finish()
    }
}
