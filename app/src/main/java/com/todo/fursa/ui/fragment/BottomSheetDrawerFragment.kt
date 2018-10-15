package com.todo.fursa.ui.fragment

import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationView
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.squareup.picasso.Picasso
import com.todo.fursa.R
import com.todo.fursa.ui.activity.AuthActivity
import com.todo.fursa.ui.viewmodel.MainViewModel
import de.hdodenhof.circleimageview.CircleImageView

class BottomSheetDrawerFragment: BottomSheetDialogFragment() {
    private lateinit var userNameTextView: TextView
    private lateinit var userEmailTextView: TextView
    private lateinit var userAva: CircleImageView
    private lateinit var navigationView: NavigationView

    private lateinit var viewModel: MainViewModel

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val currentUser: FirebaseUser = firebaseAuth.currentUser!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val rootView = inflater.inflate(R.layout.fragment_bottom_sheet_drawer, container, false)
        userNameTextView = rootView.findViewById(R.id.userNameTextView)
        userEmailTextView = rootView.findViewById(R.id.userEmailTextView)
        userAva = rootView.findViewById(R.id.civAvatar)
        navigationView = rootView.findViewById(R.id.navigationView)

        userNameTextView.text = currentUser.displayName
        userEmailTextView.text = currentUser.email
        Picasso.get().load(currentUser.photoUrl).into(userAva)

        navigationView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.menu_sign_out -> {
                    firebaseAuth.signOut()
                    startActivity(Intent(this.activity, AuthActivity::class.java))
                    this.activity!!.finish()
                }

                R.id.menu_done_tasks -> {
                  Log.d(LOG_TAG, "ViewModel size = ${viewModel.size().value}")
                }

                R.id.menu_settings -> {
                    Toast.makeText(context, "Settings", Toast.LENGTH_LONG).show()
                }
            }
            true
        }

        return rootView

    }


    companion object {
        fun newInstance(): BottomSheetDrawerFragment {
            val drawerDialog = BottomSheetDrawerFragment()
            val args = Bundle()
            drawerDialog.arguments = args
            return drawerDialog
        }

        const val LOG_TAG = "Todo/Drawer"
    }
}