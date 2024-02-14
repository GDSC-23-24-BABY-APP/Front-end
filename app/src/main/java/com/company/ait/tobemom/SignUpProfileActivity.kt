package com.company.ait.tobemom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivitySignupProfileBinding


class SignUpProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //뒤로가기
        goBack()
        //다음 버튼
        goNext()
    }

    private fun goBack() {
        binding.profileBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun goNext() {
        binding.profileNextBtn.setOnClickListener {
            val intent = Intent(this, SignUpFinActivity::class.java)
            startActivity(intent)
        }
    }
}