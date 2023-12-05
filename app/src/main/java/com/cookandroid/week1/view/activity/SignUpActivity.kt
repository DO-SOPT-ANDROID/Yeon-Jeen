package com.cookandroid.week1.view.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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

        setupListeners()
        observeSignUpResult()

    }
    private fun setupListeners() {
        binding.etSignId.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                signUpViewModel.onIdChanged(s.toString())
                updateButtonState()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.etSignPs.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                signUpViewModel.onPasswordChanged(s.toString())
                updateButtonState()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

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
    }
    private fun observeSignUpResult() {
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

        val isIdValid = signUpViewModel.isIdValid.value ?: false
        val isPasswordValid = signUpViewModel.isPasswordValid.value ?: false
        val isNicknameValid = nickname.isNotBlank() && !nickname.matches(Regex("\\s+"))
        val isAddressValid = address.isNotBlank() && !address.matches(Regex("\\s+"))

        return isIdValid && isPasswordValid && isNicknameValid && isAddressValid
    }
    private fun updateButtonState() {
        val id = binding.etSignId.text.toString()
        val password = binding.etSignPs.text.toString()
        val nickname = binding.etSingNn.text.toString()
        val address = binding.etSignAd.text.toString()

        val isIdValid = signUpViewModel.isIdValid.value ?: false
        val isPasswordValid = signUpViewModel.isPasswordValid.value ?: false
        val isNicknameValid = nickname.isNotBlank() && !nickname.matches(Regex("\\s+"))
        val isAddressValid = address.isNotBlank() && !address.matches(Regex("\\s+"))

        val isButtonEnabled = isIdValid && isPasswordValid &&
                binding.etSignId.text.isNotBlank() && binding.etSignPs.text.isNotBlank()

        binding.btnSignSign.isEnabled = isButtonEnabled

        if (!isPasswordValid) {
            binding.etSignPs.backgroundTintList = ContextCompat.getColorStateList(
                this,
                R.color.warning_color
            )
            binding.etSignPs.error = getString(R.string.invalid_ps_message)
        } else {
            binding.etSignPs.backgroundTintList = ContextCompat.getColorStateList(
                this,
                R.color.white
            )
            binding.etSignPs.error = null
        }
        binding.btnSignSign.backgroundTintList = ContextCompat.getColorStateList(
            this,
            if (isButtonEnabled) R.color.active_button
            else R.color.inactive_button
        )
    }

}


