package com.cookandroid.week1.api.request

import com.cookandroid.week1.api.respond.SupportRespond
import com.cookandroid.week1.api.respond.UserListResponse
import kotlinx.serialization.SerialName

data class UserListRequest(
    @SerialName("page")
    val page: Int = 1,
    @SerialName("per_page")
    val per_page: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("total_pages")
    val total_pages: Int,
    @SerialName("data")
    val data: List<UserListResponse>,
    @SerialName("support")
    val support: SupportRespond,

)
