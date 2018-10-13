package com.todo.fursa.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.todo.fursa.R
import com.todo.fursa.ui.base.BaseActivity
import com.todo.fursa.ui.fragment.AddTodoBottomSheet
import com.todo.fursa.ui.fragment.BottomSheetDrawerFragment
import com.todo.fursa.ui.recycler.adapter.BaseListAdapter
import com.todo.fursa.ui.recycler.adapter.TodoRecyclerAdapter
import com.todo.fursa.ui.viewmodel.MainViewModel
import com.todo.fursa.util.IListCallback
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject

class MainActivity : BaseActivity(), IListCallback {
    private lateinit var adapter: BaseListAdapter
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun getContentViewLayout() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBottomAppBar(bottomAppBar)

        viewModel = ViewModelProviders.of(this@MainActivity).get(MainViewModel::class.java)

        adapter = TodoRecyclerAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = adapter

        viewModel.getAll().observe(this@MainActivity, Observer {
            if (it!!.isEmpty()) {
                recyclerView.visibility = View.GONE
                messageTextView.visibility = View.VISIBLE
            } else {
                messageTextView.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE

                Collections.sort(it)

                adapter.addAll(it)

            }
        })


        fab.setOnClickListener {
            val dialog = AddTodoBottomSheet.newInstance()
            dialog.show(supportFragmentManager, dialog.tag)
        }
    }

    override fun onShowTodoDetails(todoId: Long) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        Log.d(MAIN_TAG, "Id = $todoId at the moment")
        intent.putExtra(EXTRA_NOTE_ID, todoId)
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_settings -> { Toast.makeText(baseContext, "Settings", Toast.LENGTH_LONG).show() }
            R.id.menu_done_tasks -> { viewModel.clearAll() }
            R.id.menu_sign_out -> {
                firebaseAuth.signOut()
                startActivity(Intent(this@MainActivity, AuthActivity::class.java))
                this.finish()
            }
            android.R.id.home -> {
                val navDrawerFragment = BottomSheetDrawerFragment.newInstance()
                navDrawerFragment.show(supportFragmentManager, navDrawerFragment.tag)
            }
        }

        return true
    }

    companion object {
        const val MAIN_TAG = "Todo/MainActivity"
        const val EXTRA_NOTE_ID = "note_id"
    }
}
