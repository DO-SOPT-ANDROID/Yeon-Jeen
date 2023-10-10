package com.cookandroid.week1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.week1.databinding.ActivitySignupBinding


class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.signbtn2.setOnClickListener {
            if (SignUpRegistration()) {

                val id = binding.eID2.text.toString()
                val password = binding.ePS2.text.toString()
                val nickname = binding.eNN1.text.toString()
                val address = binding.eAD1.text.toString()


                val intent = Intent(this, LoginActivity::class.java).apply {
                    putExtra("id", id)
                    putExtra("password", password)
                    putExtra("nickname", nickname)
                    putExtra("address", address)
                }


                Toast.makeText(this, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show()


                startActivity(intent)
            } else {

                Toast.makeText(this, "회원가입에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun SignUpRegistration(): Boolean {
        val id = binding.eID2.text.toString()
        val password = binding.ePS2.text.toString()
        val nickname = binding.eNN1.text.toString()
        val address = binding.eAD1.text.toString()


        val isIdValid = id.length in 6..10
        val isPasswordValid = password.length in 8..12
        val isNicknameValid = nickname.isNotBlank() && !nickname.matches(Regex("\\s+"))
        val isAddressValid = address.isNotBlank() && !address.matches(Regex("\\s+"))

        return isIdValid && isPasswordValid && isNicknameValid && isAddressValid
    }
}
