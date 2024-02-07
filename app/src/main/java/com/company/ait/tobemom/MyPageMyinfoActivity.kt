package com.company.ait.tobemom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivityMypageMyinfoBinding

class MyPageMyinfoActivity : AppCompatActivity() {

    lateinit var binding: ActivityMypageMyinfoBinding

    private var isIdexist: Boolean = false  //아이디 존재 여부 확인

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageMyinfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //뒤로가기
        goBack()

        //아이디 중복 체크
        duplCheck()
    }

    private fun goBack() {
        binding.myinfoBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun duplCheck() {
        binding.myinfoDuplcheckBtn.setOnClickListener {
            //입력한 아이디가 있는지 체크
            if (isIdexist) {  //아이디가 없는 경우
                binding.myinfoIdcheckyesTv.visibility = View.VISIBLE
                binding.myinfoIdchecknoTv.visibility = View.GONE
            } else {  //아이디가 중복 된 경우
                binding.myinfoIdcheckyesTv.visibility = View.GONE
                binding.myinfoIdchecknoTv.visibility = View.VISIBLE
            }
        }
    }
}