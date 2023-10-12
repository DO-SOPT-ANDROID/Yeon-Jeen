package com.cookandroid.week1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity;
import com.cookandroid.week1.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.signbtn1.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.logbtn1.setOnClickListener {
            val enteredId = binding.eID1.text.toString()
            val enteredPassword = binding.ePS1.text.toString()


            if (checkLogin(enteredId, enteredPassword)) {

                Toast.makeText(this, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {

                Toast.makeText(this, "로그인에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkLogin(enteredId: String, enteredPassword: String): Boolean {

        val registeredId = intent.getStringExtra("id")
        val registeredPassword = intent.getStringExtra("password")

        return enteredId == registeredId && enteredPassword == registeredPassword
    }

}



