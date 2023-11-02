package com.cookandroid.week1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.week1.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val loginLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            result.data?.let { data ->
                val id = data.getStringExtra("id")
                val nickname = data.getStringExtra("nickname")
                val address = data.getStringExtra("address")

                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("id", id)
                    putExtra("nickname", nickname)
                    putExtra("address", address)
                }

                startActivity(intent)
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoginSign.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            loginLauncher.launch(intent)
        }

        binding.btnLoginLogin.setOnClickListener {
            val enteredId = binding.etLoginId.text.toString()
            val enteredPassword = binding.etLoginPs.text.toString()


            if (checkLogin(enteredId, enteredPassword)) {
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("id", enteredId)
                    val nickname = intent.getStringExtra("nickname")
                    putExtra("nickname", nickname)

                    val address = intent.getStringExtra("address")
                    putExtra("address", address)


                }
                startActivity(intent)
                Toast.makeText(this, "로그인에 성공하셨습니다.", Toast.LENGTH_SHORT).show()

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



