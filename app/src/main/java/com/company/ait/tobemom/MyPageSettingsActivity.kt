package com.company.ait.tobemom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivityMypageSettingsBinding

class MyPageSettingsActivity : AppCompatActivity() {

    lateinit var binding: ActivityMypageSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //뒤로가기
        goBack()
        //휴대폰 번호 변경
        goPhonenumSet()
        //달력 설정
        goCalendarSet()
        //아기 등록
        goRegistBaby()
    }

    private fun goBack() {
        binding.settingsBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun goPhonenumSet() {
        binding.settingsPhonesetBtn.setOnClickListener {
            val intent = Intent(this, MyPagePhonesetActivity::class.java)
            startActivity(intent)
        }
    }

    private fun goCalendarSet() {
        binding.settingsCalendarsetBtn.setOnClickListener {
            val intent = Intent(this, MyPageCalendarsetActivity::class.java)
            startActivity(intent)
        }
    }

    private fun goRegistBaby() {
        binding.settingsAddbabyBtn.setOnClickListener {
            val intent = Intent(this, AddbabyActivity::class.java)
            startActivity(intent)
        }
    }
}