package com.cookandroid.week1.api

import com.cookandroid.week1.api.respond.UserListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserAuthService {
    @GET("api/users")
    fun getUsers(
        @Query("page") page: Int,
    ): Call<UserListResponse>
}
