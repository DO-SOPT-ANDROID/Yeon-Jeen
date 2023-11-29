package com.cookandroid.week1.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cookandroid.week1.R
import com.cookandroid.week1.databinding.ActivityMainBinding
import com.cookandroid.week1.view.fragment.DoAndroidFragment
import com.cookandroid.week1.view.fragment.HomeFragment
import com.cookandroid.week1.view.fragment.MyPageFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var id: String? = null
    private var nickname: String? = null
    private var address: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getStringExtra("id")
        nickname = intent.getStringExtra("nickname")
        address = intent.getStringExtra("address")

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fcv_home, HomeFragment())
                .commit()
        }

        clickBottomNavigation()
    }

    private fun clickBottomNavigation() {
        binding.bnvHome.setOnItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.menu_home -> HomeFragment()
                R.id.menu_do_android -> DoAndroidFragment()
                R.id.menu_mypage -> {
                    val myPageFragment = MyPageFragment()

                    val bundle = Bundle()
                    bundle.putString("id", id)
                    bundle.putString("nickname", nickname)
                    bundle.putString("address", address)
                    myPageFragment.arguments = bundle

                    myPageFragment
                }

                else -> HomeFragment()
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.fcv_home, fragment)
                .commit()

            return@setOnItemSelectedListener true
        }
    }
}
