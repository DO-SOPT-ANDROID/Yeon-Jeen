package com.cookandroid.week1.api.respond

import kotlinx.serialization.SerialName

data class SupportRespond(
    @SerialName("url")
    val url: String,
    @SerialName("text")
    val text: String,
)
