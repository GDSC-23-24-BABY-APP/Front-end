package com.company.ait.tobemom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivityChangeBabyBinding


class ChangeBabyActivity : AppCompatActivity() {

    lateinit var binding: ActivityChangeBabyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityChangeBabyBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}