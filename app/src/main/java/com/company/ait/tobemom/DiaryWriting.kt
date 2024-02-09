package com.company.ait.tobemom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatButton

class DiaryWriting : AppCompatActivity() {
    private lateinit var diaryBackBtn: ImageButton
    private lateinit var diaryBtn: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary_writing)

        diaryBackBtn = findViewById(R.id.DiaryWrite_back_btn)
        diaryBtn = findViewById(R.id.diaryBtn)

        //TODO editText 데이터베이스에 저장하는 코드 작성

        diaryBackBtn.setOnClickListener {
            // 이전 화면으로 이동하는 코드 작성
            val intent = Intent(this, DiaryFragment::class.java)
            startActivity(intent)
        }
        // 다음 버튼 클릭 시 이벤트 처리
        diaryBtn.setOnClickListener {
            // 다음 화면으로 이동하는 코드 작성
            val intent = Intent(this, DiaryEmoji::class.java)
            startActivity(intent)
        }
    }
}