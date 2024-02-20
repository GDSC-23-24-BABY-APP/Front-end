package com.company.ait.tobemom

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivityMainBinding
import com.kakao.sdk.common.util.Utility

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigation()

        val keyHash = Utility.getKeyHash(this)
        Log.d("Hash", keyHash)

    }
    private fun initBottomNavigation(){

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.mainBnv.setOnItemSelectedListener{ item ->
            when (item.itemId) {

                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.diaryFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, CalendarFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.checkFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, Checklist())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.newsFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, NewsFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.myPageFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, MypageFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }

    }
}