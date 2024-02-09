package com.company.ait.tobemom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivityAppOnboardingBinding

class AppOnboardingActivity : AppCompatActivity() {

    lateinit var binding: ActivityAppOnboardingBinding
    private val images = listOf(
        R.drawable.demo_home_onboarding,
        R.drawable.demo_mypage_onboarding
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = OnboardingVPAdapter(this, images)
        //binding.onboardingShowimageVp.adapter = adapter


        //뒤로 가기
        goBack()
    }

    private fun goBack() {
        binding.onboardingBackBtn.setOnClickListener {
            finish()
        }
    }
}