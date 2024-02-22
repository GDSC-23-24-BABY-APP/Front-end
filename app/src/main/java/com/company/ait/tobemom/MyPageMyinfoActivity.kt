package com.company.ait.tobemom

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivityMypageMyinfoBinding

class MyPageMyinfoActivity : AppCompatActivity() {

    lateinit var binding: ActivityMypageMyinfoBinding

    private var isIdexist: Boolean = false  //아이디 존재 여부 확인

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageMyinfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Intent에서 jwt 값을 받아옴
        val jwt = intent.getStringExtra("jwt")

        // 받아온 jwt 값 사용 예시
        if (jwt != null) {
            // jwt 값이 null이 아닌 경우 처리

        } else {
            Log.d("Myinfo", "jwt 값 null임")
        }


        //뒤로가기
        goBack()

        //아이디 중복 체크
        duplCheck()

        //이름, 아이디, 비밀번호, 혈액형, 키, 몸무게 저장
        saveInfo()
        //수정하기 버튼 클릭 이벤트
        editBtn()
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

    private fun saveInfo() {
        //이름, 아이디, 비밀번호, 혈액형(ABOAB/RH+-), 키, 몸무게 저장
    }

    private fun editBtn() {
        binding.myinfoEditfinBtn.setOnClickListener {
            Toast.makeText(this, "회원 정보가 수정되었습니다.", Toast.LENGTH_SHORT).show()
            //finish()
        }
    }
}