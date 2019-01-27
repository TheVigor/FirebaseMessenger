package com.noble.activity.firebasemessenger.models

data class User(val uid: String, val username: String, val profileImageUrl: String) {
    constructor(): this("", "", "")
}