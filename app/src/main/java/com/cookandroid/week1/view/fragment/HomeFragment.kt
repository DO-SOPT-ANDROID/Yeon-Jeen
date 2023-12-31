package com.cookandroid.week1.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cookandroid.week1.MultiViewTypeAdapter
import com.cookandroid.week1.api.ServicePool
import com.cookandroid.week1.api.respond.UserListResponse
import com.cookandroid.week1.data.UserInformation
import com.cookandroid.week1.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var multiViewTypeAdapter: MultiViewTypeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        getUsers()
    }

    private fun initAdapter() {
        multiViewTypeAdapter = MultiViewTypeAdapter()
        binding.rvFriends.adapter = multiViewTypeAdapter
    }

    private fun getUsers() {
        ServicePool.reqresService.getUsers(page = 1).enqueue(
            object : Callback<UserListResponse> {
                override fun onResponse(
                    call: Call<UserListResponse>,
                    response: Response<UserListResponse>,
                ) {
                    if (response.isSuccessful) {
                        val userListResponse = response.body()

                        userListResponse?.let { userResponse ->
                            val friendList = userResponse.data.map {
                                UserInformation.Friend(
                                    profileImage = it.avatar,
                                    name = "${it.first_name} ${it.last_name}",
                                    type = "",
                                    self_description = it.email,
                                )
                            }
                            multiViewTypeAdapter.setFriendList(friendList)
                        }
                    } else {
                        Log.e("getusers", response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<UserListResponse>, t: Throwable) {
                    Log.e("failure", "서버 연결 실패", t)
                }
            },
        )
    }
}
