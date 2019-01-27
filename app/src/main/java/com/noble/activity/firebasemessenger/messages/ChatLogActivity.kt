package com.noble.activity.firebasemessenger.messages

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.noble.activity.firebasemessenger.R
import com.noble.activity.firebasemessenger.models.ChatFromItem
import com.noble.activity.firebasemessenger.models.ChatToItem
import com.noble.activity.firebasemessenger.models.User
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chat_log.*

class ChatLogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)

        val user = intent.getParcelableExtra<User>(NewMessageActivity.USER_KEY)
        supportActionBar?.title = user.username

        val adapter = GroupAdapter<ViewHolder>()

        adapter.add(ChatFromItem())
        adapter.add(ChatToItem())
        adapter.add(ChatFromItem())
        adapter.add(ChatToItem())

        recycler_view_chat_log.adapter = adapter

    }
}
