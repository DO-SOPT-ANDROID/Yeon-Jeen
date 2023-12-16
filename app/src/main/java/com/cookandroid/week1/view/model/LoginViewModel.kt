package com.cookandroid.week1.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cookandroid.week1.api.ServicePool
import com.cookandroid.week1.api.request.ProfileRequest
import com.cookandroid.week1.api.respond.ProfileRespond
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    val loginResult: MutableLiveData<ProfileRespond?> = MutableLiveData()
    private val authService = ServicePool.authService

    fun signIn(id: String, password: String) {
        authService.login(ProfileRequest(id, password))
            .enqueue(object : Callback<ProfileRespond> {
                override fun onResponse(
                    call: Call<ProfileRespond>,
                    response: Response<ProfileRespond>,
                ) {
                    if (response.isSuccessful) {
                        loginResult.value = response.body()
                    } else {
                        loginResult.value = null
                    }
                }

                override fun onFailure(call: Call<ProfileRespond>, t: Throwable) {
                    loginResult.value = null
                }
            })
    }
}
