package com.company.ait.tobemom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.company.ait.tobemom.utils.RetrofitClient2

class CheckHealthResult : AppCompatActivity() {

    private lateinit var checkHealthBackBtn: ImageButton
    private lateinit var dateTextView: TextView
    private lateinit var WeightResultTv: TextView
    private lateinit var Emoji1: ImageButton; private lateinit var Emoji2: ImageButton
    private lateinit var Emoji3: ImageButton; private lateinit var Emoji4: ImageButton
    private lateinit var Emoji5: ImageButton; private lateinit var Emoji6: ImageButton
    private lateinit var Emoji7: ImageButton; private lateinit var Emoji8: ImageButton
    private lateinit var Emoji9: ImageButton; private lateinit var Emoji10: ImageButton
    private lateinit var Emoji11: ImageButton; private lateinit var Emoji12: ImageButton
    private lateinit var Emoji13: ImageButton; private lateinit var Emoji14: ImageButton
    private lateinit var Emoji15: ImageButton; private lateinit var Emoji16: ImageButton
    private lateinit var Emoji17: ImageButton; private lateinit var Emoji18: ImageButton
    private lateinit var Emoji19: ImageButton; private lateinit var Emoji20: ImageButton
    private lateinit var Emoji21: ImageButton; private lateinit var Emoji22: ImageButton
    private lateinit var Emoji23: ImageButton; private lateinit var Emoji24: ImageButton
    private lateinit var WriteHealthResultTv: TextView
    private lateinit var VolumeBtn: ImageButton;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_health_result)

        checkHealthBackBtn = findViewById(R.id.CheckHealth_back_btn)
        dateTextView = findViewById(R.id.dateTextView)
        WeightResultTv = findViewById(R.id.weightResultTv)
        Emoji1 = findViewById(R.id.emoji1); Emoji2 = findViewById(R.id.emoji2)
        Emoji3 = findViewById(R.id.emoji3); Emoji4 = findViewById(R.id.emoji4)
        Emoji5 = findViewById(R.id.emoji5); Emoji6 = findViewById(R.id.emoji6)
        Emoji7 = findViewById(R.id.emoji7); Emoji8 = findViewById(R.id.emoji8)
        Emoji9 = findViewById(R.id.emoji9); Emoji10 = findViewById(R.id.emoji10)
        Emoji11 = findViewById(R.id.emoji11); Emoji12 = findViewById(R.id.emoji12)
        Emoji13 = findViewById(R.id.emoji13); Emoji14 = findViewById(R.id.emoji14)
        Emoji15 = findViewById(R.id.emoji15); Emoji16 = findViewById(R.id.emoji16)
        Emoji17 = findViewById(R.id.emoji17); Emoji18 = findViewById(R.id.emoji18)
        Emoji19 = findViewById(R.id.emoji19); Emoji20 = findViewById(R.id.emoji20)
        Emoji21 = findViewById(R.id.emoji21); Emoji22 = findViewById(R.id.emoji22)
        Emoji23 = findViewById(R.id.emoji23); Emoji24 = findViewById(R.id.emoji24)
        WriteHealthResultTv = findViewById(R.id.writeyourHealthResultTv)

        //Text to Speech
        VolumeBtn = findViewById(R.id.ck_btn_volume)
        VolumeBtn.setOnClickListener {
            //TODO
        }

        checkHealthBackBtn.setOnClickListener {
            // submitBtn 클릭 시 checkCalFragment로 이동
            val checkCalFragment = CheckCalFragment()

            val fragmentManager = supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.checkHealthResult,checkCalFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        // CheckHealthResult 액티비티에서 데이터 받아오기
        val healthData: RetrofitClient2.HealthData? =
            intent.getParcelableExtra("healthData")

        // 전달받은 선택된 날짜 표시
        val selectedDate: String? = intent.getStringExtra("selectedDate")
        dateTextView.text = selectedDate

        // HealthData가 null이 아니면 UI 업데이트
        healthData?.let { updateUI(it) }

    }
    private fun updateUI(healthDataList: RetrofitClient2.HealthData){
        //Weight 업데이트
        WeightResultTv.text = "${healthDataList.weight} KG"

        //HealthInfo 업데이트
        updateHealthInfoButtonState(healthDataList.healthInfoList)
        Log.d("HealthInfo", "${healthDataList.healthInfoList.toString()}")

        //HealthDiary 업데이트
        WriteHealthResultTv.text= healthDataList.healthDiary
        Log.d("HealthInfo", "${healthDataList.healthDiary}")
    }
    private fun updateHealthInfoButtonState(healthInfoList: List<Int>) {
        // Emoji 버튼들을 리스트에 저장
        val emojiButtons = listOf(
            Emoji1, Emoji2, Emoji3, Emoji4, Emoji5, Emoji6, Emoji7, Emoji8,
            Emoji9, Emoji10, Emoji11, Emoji12, Emoji13, Emoji14, Emoji15, Emoji16,
            Emoji17, Emoji18, Emoji19, Emoji20, Emoji21, Emoji22, Emoji23, Emoji24
        )

        // HealthInfoList에 있는 값을 이용하여 Emoji 버튼을 선택 상태로 변경
        for (healthInfo in healthInfoList) {
            // healthInfo 값에 해당하는 인덱스의 이미지 버튼을 선택 상태로 변경
            emojiButtons.getOrNull(healthInfo - 1)?.isSelected = true
            // 배경색 업데이트
            updateButtonBackground(emojiButtons.getOrNull(healthInfo - 1))
        }
    }
    private fun updateButtonBackground(button: ImageButton?) {
        button?.let {
            if (it.isSelected) {
                // 선택된 경우 배경색 설정
                it.setBackgroundColor(ContextCompat.getColor(this, R.color.mainColor))
            } else {
                // 선택이 해제된 경우 배경색 초기화
                it.setBackgroundColor(ContextCompat.getColor(this, R.color.subColor))
            }
        }
    }
}