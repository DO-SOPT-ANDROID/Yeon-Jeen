package com.cookandroid.week1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.cookandroid.week1.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMyPageBinding
    private lateinit var mbtiSpinner: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageBinding.inflate(inflater, container, false)
        val view = binding.root

        val nickname = arguments?.getString("nickname")
        val id = arguments?.getString("id")
        val address = arguments?.getString("address")

        binding.tvMypageNickname.text = nickname
        binding.tvMypageEditid.text = id
        binding.tvMypageEditadress.text = address

        mbtiSpinner = view.findViewById(R.id.sp_mypage_mbti)
        val yearAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.mbti_type,
            android.R.layout.simple_spinner_item
        )
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mbtiSpinner.adapter = yearAdapter

        return view
    }




}
