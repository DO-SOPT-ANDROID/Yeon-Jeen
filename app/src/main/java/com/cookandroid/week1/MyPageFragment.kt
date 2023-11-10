package com.cookandroid.week1

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.cookandroid.week1.databinding.FragmentMyPageBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMyPageBinding
    private lateinit var mbtiSpinner: Spinner
    private lateinit var calendar: Calendar

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


        mbtiSpinner = binding.spMypageMbti

        mbtiSpinner = view.findViewById(R.id.sp_mypage_mbti)
        val mbtiAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.mbti_type,
            android.R.layout.simple_spinner_item
        )
        mbtiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mbtiSpinner.adapter = mbtiAdapter

        calendar = Calendar.getInstance()

        binding.btnMypageDate.setOnClickListener {
            showDatePicker()
        }

        return view
    }

    private fun showDatePicker() {
        DatePickerDialog(requireContext()).apply {
            setOnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
            updateDate(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            show()
        }
    }


    private fun updateDateInView() {
        val myFormat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myFormat, Locale.KOREA)
        binding.tvMypagePickdate.text = sdf.format(calendar.time)
    }


}
