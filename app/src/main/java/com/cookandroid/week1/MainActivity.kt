package com.cookandroid.week1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.week1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val nickname = intent.getStringExtra("nickname")
        val id = intent.getStringExtra("id")
        val address = intent.getStringExtra("address")


        binding.tNN1.text = "$nickname"
        binding.tID4.text = "$id"
        binding.tAD3.text = "$address"


    }
}
