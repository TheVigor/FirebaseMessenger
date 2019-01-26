package com.noble.activity.firebasemessenger

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        register_button.setOnClickListener {
            performRegister()
        }

        already_have_account_input.setOnClickListener {
            Log.d("MainActivity", "Try to show login activity")

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun performRegister() {
        val email = email_input.text.toString()
        val password = password_input.text.toString()

        Log.d("MainActivity", "Email is $email")
        Log.d("MainActivity", "Password is $password")

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Empty", Toast.LENGTH_LONG).show()
            return
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                if (!it.isSuccessful) return@addOnCompleteListener

                Log.d("MainActivity", "Success:" + it.result?.user?.uid)
            }
            .addOnFailureListener{
                Log.d("MainActivity", "Failed to create user: ${it.message}")
                Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_LONG).show()
            }
    }
}
