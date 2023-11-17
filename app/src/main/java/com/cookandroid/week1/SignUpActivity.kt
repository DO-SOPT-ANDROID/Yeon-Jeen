package com.cookandroid.week1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.week1.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSignSign.setOnClickListener {
            if (SignUpRegistration()) {
                val id = binding.etSignId.text.toString()
                val password = binding.etSignPs.text.toString()
                val nickname = binding.etSingNn.text.toString()
                val address = binding.etSignAd.text.toString()

                signUp(password, id, nickname, address)

            } else {
                Toast.makeText(this, "회원가입에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
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

    private fun signUp(password: String, id: String, nickname: String, address: String) {
        ServicePool.authService.signup(SignUpRequest(id, nickname, password)).enqueue(
            object : retrofit2.Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful) {
                        val intent = Intent(this@SignUpActivity, LoginActivity::class.java).apply {
                            putExtra("id", id)
                            putExtra("password", password)
                            putExtra("nickname", nickname)
                            putExtra("address", address)
                        }

                        Toast.makeText(this@SignUpActivity, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT)
                            .show()

                        startActivity(intent)
                        finish()
                    }
                    //ww1234
                    else {
                        Toast.makeText(this@SignUpActivity, "회원가입에 실패하셨습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    t.printStackTrace()
                }
            }
        )
    }
}



