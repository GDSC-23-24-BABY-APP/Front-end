package com.company.ait.tobemom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivitySignupFindpwBinding

class SignUpFindpwActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupFindpwBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupFindpwBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //뒤로가기
        goBack()

        //아이디 찾기 이동
        goId()

        //비밀번호 재설정 클릭 이벤트(dialog 띄우기)
        showDialog()
    }

    private fun goBack() {
        binding.findpwBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun goId() {
        binding.findpwIdTv.setOnClickListener {
            val intent = Intent(this, SignUpFindidActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showDialog() {
        binding.findpwResetBtn.setOnClickListener {
            val dialog = CustomFindidDialog(binding.root.context)
            dialog.show()
        }
    }
}