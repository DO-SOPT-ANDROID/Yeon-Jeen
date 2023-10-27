package com.cookandroid.week1

import androidx.annotation.DrawableRes


data class Friend(
    @DrawableRes val profileImage: Int,
    val name: String,
    val self_description: String,
    val type: String,

    )
