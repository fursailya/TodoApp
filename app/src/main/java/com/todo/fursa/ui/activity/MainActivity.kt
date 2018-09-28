package com.todo.fursa.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.bottomappbar.BottomAppBar
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.todo.fursa.R
import com.todo.fursa.room.database.AppDatabase
import com.todo.fursa.ui.fragment.AddTodoBottomSheet
import com.todo.fursa.ui.recycler.BaseListAdapter
import com.todo.fursa.ui.recycler.TodoRecyclerAdapter
import com.todo.fursa.ui.viewmodel.MainViewModel
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var fab: FloatingActionButton
    private lateinit var messageTextView: TextView

    private lateinit var adapter: BaseListAdapter
    private lateinit var recyclerView: RecyclerView

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messageTextView = findViewById(R.id.messageTextView)

        viewModel = ViewModelProviders.of(this@MainActivity).get(MainViewModel::class.java)

        adapter = TodoRecyclerAdapter()
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        viewModel.getAll().observe(this@MainActivity, Observer {
            if(it!!.isEmpty()) {
                recyclerView.visibility = View.GONE
                messageTextView.visibility = View.VISIBLE
            } else {
                Collections.sort(it)
                messageTextView.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                adapter.addAll(it)
                recyclerView.adapter = adapter
            }
        })



        bottomAppBar = findViewById(R.id.bottom_appbar)
        fab = findViewById(R.id.fab)

        setSupportActionBar(bottomAppBar)

        fab.setOnClickListener {
          val dialog = AddTodoBottomSheet.newInstance()
          dialog.show(supportFragmentManager, dialog.tag)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId) {
            R.id.menu_settings -> {

            }

            R.id.menu_done_tasks -> {
                viewModel.clearAll()
            }
        }

        return true
    }

    companion object {
        const val MAIN_TAG = "Todo/MainActivity"
    }
}
