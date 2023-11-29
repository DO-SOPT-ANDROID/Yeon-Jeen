package com.cookandroid.week1.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cookandroid.week1.R
import com.cookandroid.week1.databinding.ActivitySignupBinding
import com.cookandroid.week1.view.model.SignUpViewModel

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        signUpViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        binding.btnSignSign.setOnClickListener {
            if (SignUpRegistration()) {
                val id = binding.etSignId.text.toString()
                val password = binding.etSignPs.text.toString()
                val nickname = binding.etSingNn.text.toString()
                val address = binding.etSignAd.text.toString()

                signUpViewModel.signUp(password, id, nickname, address)
            } else {
                Toast.makeText(this, "회원가입에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
            }
        }
        signUpViewModel.signUpResult.observe(this) { success ->
            if (success) {
                val id = binding.etSignId.text.toString()
                val password = binding.etSignPs.text.toString()
                val nickname = binding.etSingNn.text.toString()
                val address = binding.etSignAd.text.toString()

                val intent = Intent(this@SignUpActivity, LoginActivity::class.java).apply {
                    putExtra("id", id)
                    putExtra("password", password)
                    putExtra("nickname", nickname)
                    putExtra("address", address)
                }

                startActivity(intent)
                Toast.makeText(
                    this@SignUpActivity,
                    getString(R.string.sucsingup),
                    Toast.LENGTH_SHORT,
                ).show()
                finish()
            } else {
                Toast.makeText(
                    this@SignUpActivity,
                    getString(R.string.failsignup),
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }
    }

    private fun SignUpRegistration(): Boolean {
        val id = binding.etSignId.text.toString()
        val password = binding.etSignPs.text.toString()
        val nickname = binding.etSingNn.text.toString()
        val address = binding.etSignAd.text.toString()

        val isIdValid = id.length in 6..10
        val isPasswordValid = password.length in 8..12
        val isNicknameValid = nickname.isNotBlank() && !nickname.matches(Regex("\\s+"))
        val isAddressValid = address.isNotBlank() && !address.matches(Regex("\\s+"))

        return isIdValid && isPasswordValid && isNicknameValid && isAddressValid
    }
}
