package com.todo.fursa

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.bottomappbar.BottomAppBar
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.todo.fursa.room.database.AppDatabase

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
            Toast.makeText(this@MainActivity, "Clicked", Toast.LENGTH_SHORT).show()
        }


        val db = AppDatabase.newInstance(baseContext)
        val dao = db!!.todoDAO

        dao.getAll().observe(this, Observer {
          Log.d(MAIN_TAG, it.toString())
        })

    }

    companion object {
        const val MAIN_TAG = "Todo/MainActivity"
    }
}
