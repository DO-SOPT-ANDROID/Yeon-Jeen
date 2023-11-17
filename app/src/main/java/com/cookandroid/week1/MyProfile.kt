package com.cookandroid.week1

import androidx.annotation.DrawableRes


sealed class A{
    data class MyProfile(
        @DrawableRes val profileImageResId: Int,
        val name: String,
        val selfDescription: String,
        val type: String,): A()

    data class Friend(
        val profileImage: String,
        val name: String,
        val self_description: String,
        val type: String,

        ): A()
}


