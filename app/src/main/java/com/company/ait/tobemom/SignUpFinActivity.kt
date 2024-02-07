package com.company.ait.tobemom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivitySignupFinBinding

class SignUpFinActivity: AppCompatActivity() {
    lateinit var binding: ActivitySignupFinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupFinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //다음 버튼
        goNext()
    }

    private fun goNext() {
        binding.signupfinNextBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}