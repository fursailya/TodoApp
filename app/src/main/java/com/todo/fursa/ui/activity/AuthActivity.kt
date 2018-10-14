package com.todo.fursa.ui.activity

import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.todo.fursa.R
import com.todo.fursa.ui.base.BaseActivity
import android.content.Intent
import androidx.annotation.NonNull
import androidx.fragment.app.FragmentActivity
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.google.android.gms.common.api.GoogleApiClient
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.ConnectionResult
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_auth.*

const val RC_SIGN_INTENT = 1

class AuthActivity: AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authListener: FirebaseAuth.AuthStateListener
    private lateinit var googleApiClient: GoogleApiClient
    private lateinit var signInAccount: GoogleSignInAccount

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        firebaseAuth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        FirebaseApp.initializeApp(this)

        authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            if (firebaseAuth.currentUser != null) {
                startActivity(Intent(this@AuthActivity, MainActivity::class.java))
                this.finish()
            }
        }

        googleApiClient = GoogleApiClient.Builder(applicationContext)
                .enableAutoManage(this) { Toast.makeText(this@AuthActivity, "Auth Error!", Toast.LENGTH_SHORT).show() }
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build()

        signInButton.setOnClickListener { signIn() }

    }

    private fun signIn() {
        val signIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
        startActivityForResult(signIntent, RC_SIGN_INTENT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RC_SIGN_INTENT) {
            val res = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if(res.isSuccess) {
                signInAccount = res.signInAccount!!
                firebaseAuthWithGoogle(signInAccount)
            }
        }
    }

    private fun firebaseAuthWithGoogle(signInAccount: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(signInAccount.idToken, null)
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener {
                    Log.d("Todo/AuthActivity", "Hello ${signInAccount.familyName}")
                    Toast.makeText(baseContext, "Hello ${signInAccount.displayName}", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@AuthActivity, MainActivity::class.java))
                }
    }

    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener(authListener)
    }

    override fun onStop() {
        super.onStop()
        if(authListener != null) {
            firebaseAuth.removeAuthStateListener(authListener)
        }
    }
}