package com.noble.activity.firebasemessenger.models

import com.noble.activity.firebasemessenger.R
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.user_row_new_message.view.*

class UserItem(val user: User): Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.username.text = user.username
        Picasso.get().load(user.profileImageUrl).into(viewHolder.itemView.user_photo)
    }

    override fun getLayout(): Int {
        return R.layout.user_row_new_message
    }

}