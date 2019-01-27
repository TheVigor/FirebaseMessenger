package com.noble.activity.firebasemessenger

import android.app.Activity
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule


fun ImageView.loadUserPhoto(photoUrl: String?) {
    if (!(context as Activity).isDestroyed) {
        GlideApp.with(this).load(photoUrl).fallback(R.drawable.abc_btn_default_mtrl_shape).into(this)
    }
}


@GlideModule
class CustomGlideModule : AppGlideModule()