package com.noble.activity.firebasemessenger.messages

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.noble.activity.firebasemessenger.R
import com.noble.activity.firebasemessenger.models.ChatFromItem
import com.noble.activity.firebasemessenger.models.ChatMessage
import com.noble.activity.firebasemessenger.models.ChatToItem
import com.noble.activity.firebasemessenger.models.User
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chat_log.*

class ChatLogActivity : AppCompatActivity() {

    companion object {
        private val TAG = "ChatLogActivity"
    }

    val adapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)

        recycler_view_chat_log.adapter = adapter

        val user = intent.getParcelableExtra<User>(NewMessageActivity.USER_KEY)
        supportActionBar?.title = user.username

        //setupDummyData()
        listenForMessages()

        send_button.setOnClickListener {
            Log.d(TAG, "Sended")
            performSendMessage()
        }

    }

    private fun listenForMessages() {
        val ref = FirebaseDatabase.getInstance().getReference("/messages")

        ref.addChildEventListener(object: ChildEventListener{
            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val chatMessage = p0.getValue(ChatMessage::class.java)

                if (chatMessage != null) {
                    Log.d(TAG, chatMessage.text)

                    if (chatMessage.fromId == FirebaseAuth.getInstance().uid) {
                        adapter.add(ChatFromItem(chatMessage.text))
                    } else {
                        adapter.add(ChatToItem(chatMessage.text))
                    }

                }
            }

            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildRemoved(p0: DataSnapshot) {

            }
        })

    }

    private fun performSendMessage() {
        val ref = FirebaseDatabase.getInstance().getReference("/messages").push()

        val user = intent.getParcelableExtra<User>(NewMessageActivity.USER_KEY)

        val fromId = FirebaseAuth.getInstance().uid
        val toId = user.uid

        val text = enter_text_input.text.toString()

        if (fromId == null) return

        val chatMessage = ChatMessage(ref.key!!, text, fromId, toId, System.currentTimeMillis() / 1000)
        ref.setValue(chatMessage)
            .addOnSuccessListener {
                Log.d(TAG, "Saved chat message: ${ref.key}")
            }
    }

    private fun setupDummyData() {
        val adapter = GroupAdapter<ViewHolder>()

        adapter.add(ChatFromItem("FROOOM MESSSAGE"))
        adapter.add(ChatToItem("TOOOO MESSAGE"))
        adapter.add(ChatFromItem("FROOOM MESSAGE"))
        adapter.add(ChatToItem("TOOOO MESSAGE"))

        recycler_view_chat_log.adapter = adapter
    }
}
