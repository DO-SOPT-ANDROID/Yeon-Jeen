package com.cookandroid.week1.data // ktlint-disable filename

import androidx.annotation.DrawableRes

sealed class UserInformation {
    data class MyProfile(
        @DrawableRes val profileImageResId: Int,
        val name: String,
        val selfDescription: String,
        val type: String,
    ) : UserInformation()

    data class Friend(
        val profileImage: String,
        val name: String,
        val self_description: String,
        val type: String,

    ) : UserInformation()
}
