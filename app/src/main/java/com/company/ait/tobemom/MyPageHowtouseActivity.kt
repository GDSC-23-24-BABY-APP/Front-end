package com.company.ait.tobemom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivityMypageHowtouseBinding

class MyPageHowtouseActivity : AppCompatActivity() {

    lateinit var binding: ActivityMypageHowtouseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageHowtouseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //뒤로 가기
        goBack()
    }

    private fun goBack() {
        binding.howtouseBackBtn.setOnClickListener {
            finish()
        }
    }
}