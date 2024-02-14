package com.company.ait.tobemom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivitySignupFindidBinding

class SignUpFindidActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupFindidBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupFindidBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //아이디 찾기 버튼 클릭 이벤트
        showDialog()

        //비밀번호 재설정 이동
        goPw()

        //뒤로가기
        goBack()
    }

    private fun showDialog() {
        binding.findidFindBtn.setOnClickListener {
            val findid = "user12@" // 사용자의 실제 아이디 정보, 추후 수정 예정
            val isExist = true // 아이디가 존재하는지 여부에 따른 값, 추후 수정 예정
            val dialog = CustomFindidDialog(binding.root.context, findid, isExist)
            dialog.show()
        }
    }

    private fun goBack() {
        binding.findidBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun goPw() {
        binding.findidPwTv.setOnClickListener {
            val intent = Intent(this, SignUpFindpwActivity::class.java)
            startActivity(intent)
        }
    }
}