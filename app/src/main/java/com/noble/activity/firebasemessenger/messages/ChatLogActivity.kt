package com.noble.activity.firebasemessenger.messages

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.noble.activity.firebasemessenger.R

class ChatLogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)

        supportActionBar?.title = "Chat Log"
    }
}
