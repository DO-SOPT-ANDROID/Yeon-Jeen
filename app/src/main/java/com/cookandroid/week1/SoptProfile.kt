package com.cookandroid.week1


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SoptProfile(
    @SerialName("password")
    val password: String,
    @SerialName("username")
    val username: String
)