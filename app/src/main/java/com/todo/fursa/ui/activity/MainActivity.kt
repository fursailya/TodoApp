package com.todo.fursa.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.bottomappbar.BottomAppBar
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.todo.fursa.R
import com.todo.fursa.room.model.Separator
import com.todo.fursa.room.model.Todo
import com.todo.fursa.ui.fragment.AddTodoBottomSheet
import com.todo.fursa.ui.recycler.BaseListAdapter
import com.todo.fursa.ui.recycler.IBaseListItem
import com.todo.fursa.ui.recycler.TodoRecyclerAdapter
import com.todo.fursa.ui.viewmodel.MainViewModel
import com.todo.fursa.util.IListCallback
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), IListCallback {
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

        adapter = TodoRecyclerAdapter(this)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        viewModel.getAll().observe(this@MainActivity, Observer {
            if (it!!.isEmpty()) {
                recyclerView.visibility = View.GONE
                messageTextView.visibility = View.VISIBLE
            } else {
                messageTextView.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE

                Collections.sort(it)

                recyclerView.adapter = adapter
                adapter.addAll(it)

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

    override fun onShowTodoDetails(todoId: Long) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra(EXTRA_NOTE_ID, todoId)
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(intent)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
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

        const val EXTRA_NOTE_ID = "note_id"
    }
}
