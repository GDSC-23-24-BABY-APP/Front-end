package com.company.ait.tobemom

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivityMypageSettingsPhonesetBinding

class MyPagePhonesetActivity : AppCompatActivity() {

    lateinit var binding: ActivityMypageSettingsPhonesetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageSettingsPhonesetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //뒤로 가기
        goBack()
        //휴대폰 번호 저장
        val phoneNum: String = binding.phonesetMakeidEt.text.toString()
        //휴대폰 번호 백에 저장

        //인증번호 확인 버튼 클릭 이벤트
        phonenumCheckBtn()
        //인증번호 일치 여부 확인
        val phoneNumCheck: String = binding.phonesetChecknumEt.text.toString()
        if (phoneNumCheck != phoneNum) {
            binding.phonesetNocheckTv.visibility = View.VISIBLE
        } else {
            binding.phonesetNocheckTv.visibility = View.GONE
        }
        //확인 버튼 클릭 이벤트
        confirmBtn()
    }

    private fun goBack() {
        binding.phonesetBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun phonenumCheckBtn() {
        binding.phonesetCheckidBtn.setOnClickListener {
            //인증번호 보내기
        }
    }

    private fun confirmBtn() {
        binding.phonesetCheckBtn.setOnClickListener {
            Toast.makeText(this, "The phone number has been updated.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}