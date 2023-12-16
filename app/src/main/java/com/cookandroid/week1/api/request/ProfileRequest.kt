package com.cookandroid.week1.api.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileRequest(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,

)
