package com.cookandroid.week1.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/members")
    fun signup(
        @Body request: SignUpRequest,
    ): Call<Unit>

    @POST("api/v1/members/sign-in")
    fun login(
        @Body request: ProfileRequest,
    ): Call<ProfileRespond>
}
