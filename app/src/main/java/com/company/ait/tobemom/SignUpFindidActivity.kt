package com.company.ait.tobemom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivitySignupFindidBinding
import com.company.ait.tobemom.databinding.ActivitySignupFindpwBinding

class SignUpFindidActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupFindidBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupFindidBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //아이디 찾기 버튼 클릭 이벤트
        showDialog()

        //뒤로가기
        goBack()

        //비밀번호 재설정 이동
        goPw()
    }

    private fun showDialog() {
        binding.findidFindBtn.setOnClickListener {
            val dialog = CustomFindidDialog(binding.root.context)
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
            val intent = Intent(this, ActivitySignupFindpwBinding::class.java)
            startActivity(intent)
        }
    }
}