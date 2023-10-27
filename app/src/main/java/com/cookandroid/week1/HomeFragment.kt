package com.cookandroid.week1
import android.os.Bundle
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
        //return super.onCreateView(inflater, container, savedInstanceState)
    }


    private val mockFriendList = listOf<Friend>(
        Friend(
            profileImage = R.drawable.ex1,
            name = "푸바오1",
            self_description = "할부지가 젤 좋아",
        ),
        Friend(
            profileImage = R.drawable.ex1,
            name = "푸바오2",
            self_description = "당근주세요",
        ),
        Friend(
            profileImage = R.drawable.ex1,
            name = "푸바오3",
            self_description = "나는야 미판다",
        ),
        Friend(
            profileImage = R.drawable.ex1,
            name = "푸바오3",
            self_description = "나는야 미판다",
        ),
        Friend(
            profileImage = R.drawable.ex1,
            name = "푸바오3",
            self_description = "나는야 미판다",
        ),
        Friend(
            profileImage = R.drawable.ex1,
            name = "푸바오3",
            self_description = "나는야 미판다",
        ),
        Friend(
            profileImage = R.drawable.ex1,
            name = "푸바오3",
            self_description = "나는야 미판다",
        ),
        Friend(
            profileImage = R.drawable.ex1,
            name = "푸바오3",
            self_description = "나는야 미판다",
        ),
        Friend(
            profileImage = R.drawable.ex1,
            name = "푸바오3",
            self_description = "나는야 미판다",
        ),

        )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val friendAdapter = FriendAdapter(requireContext())
        binding.rvFriends.adapter = friendAdapter
        friendAdapter.setFriendList(mockFriendList)
    }
}