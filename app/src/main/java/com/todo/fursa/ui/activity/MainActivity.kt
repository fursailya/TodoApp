package com.todo.fursa.ui.activity

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.bottomappbar.BottomAppBar
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import com.todo.fursa.R
import com.todo.fursa.room.database.AppDatabase
import com.todo.fursa.ui.fragment.AddTodoBottomSheet

class MainActivity : AppCompatActivity() {
    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomAppBar = findViewById(R.id.bottom_appbar)
        fab = findViewById(R.id.fab)

        setSupportActionBar(bottomAppBar)

        fab.setOnClickListener {
          val dialog = AddTodoBottomSheet.newInstance()
          dialog.show(supportFragmentManager, dialog.tag)
        }


        val db = AppDatabase.newInstance(baseContext)
        val dao = db!!.todoDAO

        dao.getAll().observe(this, Observer {
          Log.d(MAIN_TAG, it.toString())
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    companion object {
        const val MAIN_TAG = "Todo/MainActivity"
    }
}
