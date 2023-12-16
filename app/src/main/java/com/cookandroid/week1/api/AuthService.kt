package com.cookandroid.week1.api

import com.cookandroid.week1.api.request.ProfileRequest
import com.cookandroid.week1.api.request.SignUpRequest
import com.cookandroid.week1.api.respond.ProfileRespond
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
