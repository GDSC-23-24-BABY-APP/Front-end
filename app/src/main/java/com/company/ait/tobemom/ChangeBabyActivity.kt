package com.company.ait.tobemom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.ait.tobemom.databinding.ActivityChangeBabyBinding


class ChangeBabyActivity : AppCompatActivity() {

    lateinit var binding: ActivityChangeBabyBinding

    private val babyList = listOf(
        Baby("Sweety"),
        Baby("Junior")
        // Add more babies as needed
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityChangeBabyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //뒤로 가기
        binding.changebabyBackBtn.setOnClickListener {
            finish()
        }

        val adapter = ChangeBabyRVAdapter(babyList)
        binding.changebabyListRv.adapter = adapter
        binding.changebabyListRv.layoutManager = LinearLayoutManager(this)
    }
}