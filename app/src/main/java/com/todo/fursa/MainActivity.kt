package com.todo.fursa

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.todo.fursa.room.database.AppDatabase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = AppDatabase.newInstance(baseContext)
        val dao = db.todoDAO

        dao.getAll().observe(this, Observer {
          Log.d(MAIN_TAG, it.toString())
        })

    }

    companion object {
        const val MAIN_TAG = "Todo/MainActivity"
    }
}
