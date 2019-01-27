package com.noble.activity.firebasemessenger.models

import com.noble.activity.firebasemessenger.R
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.chat_from_row.view.*

class ChatFromItem(val text: String): Item<ViewHolder>() {
    override fun getLayout() = R.layout.chat_from_row

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.user_message.text = text
    }


}