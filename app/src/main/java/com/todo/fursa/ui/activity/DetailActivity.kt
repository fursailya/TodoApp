package com.todo.fursa.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.todo.fursa.R
import com.todo.fursa.ui.viewmodel.MainViewModel
import com.todo.fursa.util.fromTimestamp

class DetailActivity : AppCompatActivity() {
    private lateinit var titleTextView: TextView
    private lateinit var textTextView: TextView
    private lateinit var textViewPriority: TextView
    private lateinit var alarmTextView: TextView
    private lateinit var imageViewPriority: ImageView

    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        titleTextView = findViewById(R.id.titleTextView)
        textTextView = findViewById(R.id.textTexView)
        alarmTextView = findViewById(R.id.alarmTextView)
        textViewPriority = findViewById(R.id.priorityTextView)
        imageViewPriority = findViewById(R.id.priorityImageView)

        val id = intent.extras.getLong(MainActivity.EXTRA_NOTE_ID)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.selectById(id).observe(this, Observer {
            titleTextView.text = it!!.title
            textTextView.text = it.text
            alarmTextView.text = fromTimestamp(it.time)

            if(it.priority == 0) {
                textViewPriority.text = resources.getString(R.string.low_priority)
                imageViewPriority.setBackgroundResource(R.drawable.priority_low)
            } else if(it.priority == 1) {
                textViewPriority.text = resources.getString(R.string.high_priority)
                imageViewPriority.setBackgroundResource(R.drawable.priority_high)
            }
        })
    }
}
