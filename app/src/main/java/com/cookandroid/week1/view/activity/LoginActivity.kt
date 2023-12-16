package com.cookandroid.week1.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cookandroid.week1.R
import com.cookandroid.week1.databinding.ActivityLoginBinding
import com.cookandroid.week1.view.model.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val loginLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            result.data?.let { data ->
                val id = data.getStringExtra(getString(R.string.id))
                val nickname = data.getStringExtra(getString(R.string.nickname))
                val address = data.getStringExtra(getString(R.string.address))

                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra(getString(R.string.id), id)
                    putExtra(getString(R.string.nickname), nickname)
                    putExtra(getString(R.string.address), address)
                }

                startActivity(intent)
            }
        }

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.btnLoginSign.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            loginLauncher.launch(intent)
        }

        binding.btnLoginLogin.setOnClickListener {
            val enteredId = binding.etLoginId.text.toString()
            val enteredPassword = binding.etLoginPs.text.toString()
            viewModel.signIn(enteredId, enteredPassword)
        }
        viewModel.loginResult.observe(this) { profileRespond ->
            profileRespond?.let {
                val intent = Intent(this@LoginActivity, MainActivity::class.java).apply {
                    putExtra(getString(R.string.id), profileRespond.id)
                    putExtra(getString(R.string.nickname), profileRespond.nickname)
                }
                startActivity(intent)
                Toast.makeText(
                    this@LoginActivity,
                    getString(R.string.suclogin),
                    Toast.LENGTH_SHORT,
                ).show()
            } ?: run {
                Toast.makeText(
                    this@LoginActivity,
                    getString(R.string.faillogin),
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }
    }
}
