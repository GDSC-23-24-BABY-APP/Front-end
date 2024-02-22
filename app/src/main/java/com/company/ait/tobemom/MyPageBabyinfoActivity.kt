package com.company.ait.tobemom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivityMypageBabyinfoBinding

class MyPageBabyinfoActivity : AppCompatActivity() {

    lateinit var binding : ActivityMypageBabyinfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageBabyinfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //뒤로 가기
        goBack()

        //아기 태명, 출산예정일 출력
        binding.babyinfoBirthnameTv.text = "Sweety"  //태명 보여주기 -> 추후 텍스트 수정
        binding.babyinfoBirthdateTv.text = "2024/02/08"  //출산예정일 보여주기 -> 추후 텍스트 수정
    }

    private fun goBack() {
        binding.babyinfoBackBtn.setOnClickListener {
            finish()
        }
    }
}