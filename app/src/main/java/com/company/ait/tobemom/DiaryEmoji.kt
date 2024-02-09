package com.company.ait.tobemom

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatButton

class DiaryEmoji : AppCompatActivity() {
    private lateinit var diaryBackBtn: ImageButton
    private lateinit var diaryBackBtn2: AppCompatButton
    private lateinit var diaryNextBtn: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary_emoji)

        diaryBackBtn = findViewById(R.id.DiaryEmoji_back_btn)
        diaryBackBtn2 = findViewById(R.id.diary_back_btn2)
        diaryNextBtn = findViewById(R.id.diary_next_btn)

        diaryBackBtn.setOnClickListener {
            // 이전 화면으로 이동하는 코드 작성
            // 예를 들어, 이전 화면인 MainActivity로 이동하려면 아래와 같이 작성할 수 있습니다.
            val intent = Intent(this, DiaryWriting::class.java)
            startActivity(intent)
        }

        diaryBackBtn2.setOnClickListener {
            // 이전 화면으로 이동하는 코드 작성
            // 예를 들어, 이전 화면인 MainActivity로 이동하려면 아래와 같이 작성할 수 있습니다.
            val intent = Intent(this, DiaryWriting::class.java)
            startActivity(intent)
        }

        // 다음 버튼 클릭 시 이벤트 처리
        diaryNextBtn.setOnClickListener {
            // 다음 화면으로 이동하는 코드 작성
            //TODO 다이어리 작성완료 화면으로 이동
            ///val intent = Intent(this, ::class.java)
            //startActivity(intent)
        }
    }
}