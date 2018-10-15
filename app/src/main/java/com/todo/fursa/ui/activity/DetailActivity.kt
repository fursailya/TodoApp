package com.todo.fursa.ui.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

        imageButtonAttachFile.setOnClickListener { showAttachDialog() }

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

    private fun showAttachDialog() {
        val dialog =  AlertDialog.Builder(this)
                .setTitle(R.string.attach_dialog_title)
                .setItems(R.array.attach_variants) { dialog, which ->  when(which) {
                    0 -> Toast.makeText(baseContext, "Gallery", Toast.LENGTH_LONG).show()
                    1 -> Toast.makeText(baseContext, "From Camera", Toast.LENGTH_LONG).show()
                }}.create()

        dialog.show()
    }
}
