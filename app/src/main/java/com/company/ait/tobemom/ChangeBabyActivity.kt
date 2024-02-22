package com.company.ait.tobemom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivityChangeBabyBinding


class ChangeBabyActivity : AppCompatActivity() {

    lateinit var binding: ActivityChangeBabyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityChangeBabyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //뒤로 가기
        binding.changebabyBackBtn.setOnClickListener {
            finish()
        }
    }
}