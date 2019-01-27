package com.noble.activity.firebasemessenger.registerlogin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.noble.activity.firebasemessenger.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button.setOnClickListener {
            val email = email_input.text.toString()
            val password = password_input.text.toString()

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)

            Log.d("LoginActivity", "$email $password")
        }

        back_to_registration_input.setOnClickListener {
            finish()
        }

    }

}