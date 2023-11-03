package com.cookandroid.week1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cookandroid.week1.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    private val mockFriendList = listOf<A>(
        A.MyProfile(
            profileImageResId = R.drawable.my_profile,
            name = "연진이",
            selfDescription = "잘 부탁해",
            type = "MyProfileViewHolder"
        ),
        A.Friend(
            profileImage = R.drawable.fubao1,
            name = "푸바오1",
            self_description = "할부지가 젤 좋아",
            type = "FriendViewHolder"
        ),
        A.Friend(
            profileImage = R.drawable.fubao11,
            name = "푸바오2",
            self_description = "당근주세요",
            type = "FriendViewHolder"
        ),
        A.Friend(
            profileImage = R.drawable.fubao10,
            name = "푸바오3",
            self_description = "나는야 미판다",
            type = "FriendViewHolder"
        ),
        A.Friend(
            profileImage = R.drawable.fubao9,
            name = "푸바오3",
            self_description = "노는게 제일 좋아",
            type = "FriendViewHolder"
        ),
        A.Friend(
            profileImage = R.drawable.fubao5,
            name = "푸바오4",
            self_description = "나는 용인 푸씨",
            type = "FriendViewHolder"
        ),
        A.Friend(
            profileImage = R.drawable.fubao5,
            name = "푸바오5",
            self_description = "푸공주님",
            type = "FriendViewHolder"
        ),
        A.Friend(
            profileImage = R.drawable.fubao6,
            name = "푸바오6",
            self_description = "옆집 아저씨 누구야",
            type = "FriendViewHolder"
        ),
        A.Friend(
            profileImage = R.drawable.fubao7,
            name = "푸바오7",
            self_description = "루이후이 모여",
            type = "FriendViewHolder"
        ),
        A.Friend(
            profileImage = R.drawable.fubao8,
            name = "푸바오8",
            self_description = "나 중국 안가",
            type = "FriendViewHolder"
        ),
        A.Friend(
            profileImage = R.drawable.fubao9,
            name = "푸바오9",
            self_description = "가을이다",
            type = "FriendViewHolder"
        ),
        A.Friend(
            profileImage = R.drawable.fubao10,
            name = "푸바오10",
            self_description = "나는 뚠뚠이",
            type = "FriendViewHolder"
        ),
        A.Friend(
            profileImage = R.drawable.fubao11,
            name = "푸바오11",
            self_description = "좋다~",
            type = "FriendViewHolder"
        ),

        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("로그", "$mockFriendList")
        val multiViewTypeAdapter = MultiViewTypeAdapter(mockFriendList)
        binding.rvFriends.adapter = multiViewTypeAdapter
        multiViewTypeAdapter.setFriendList(mockFriendList)
    }

}