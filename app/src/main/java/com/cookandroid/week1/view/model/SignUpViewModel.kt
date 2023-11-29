package com.cookandroid.week1.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cookandroid.week1.api.ServicePool
import com.cookandroid.week1.api.request.SignUpRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    val signUpResult: MutableLiveData<Boolean> = MutableLiveData()
    private val authService = ServicePool.authService

    fun signUp(password: String, id: String, nickname: String, address: String) {
        authService.signup(SignUpRequest(id, nickname, password)).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                signUpResult.value = response.isSuccessful
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                signUpResult.value = false
            }
        })
    }
}
