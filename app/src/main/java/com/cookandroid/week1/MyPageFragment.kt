package com.cookandroid.week1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cookandroid.week1.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMyPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageBinding.inflate(inflater, container, false)
        val view = binding.root

        val nickname = arguments?.getString("nickname")
        val id = arguments?.getString("id")
        val address = arguments?.getString("address")

        binding.tNN1.text = nickname
        binding.tID4.text = id
        binding.tAD3.text = address

        return view
    }
}
