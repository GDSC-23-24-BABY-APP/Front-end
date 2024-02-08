package com.company.ait.tobemom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivityDiaryViewBinding

class DiaryViewActivity: AppCompatActivity() {

    lateinit var binding: ActivityDiaryViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}