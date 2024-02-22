package com.company.ait.tobemom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivityMypageNoticeboardBinding

class MyPageNoticeboardActivity : AppCompatActivity() {

    lateinit var binding: ActivityMypageNoticeboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageNoticeboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //뒤로 가기
        goBack()
        //공지 내용 설정
        setBoard()
    }

    private fun goBack() {
        binding.noticeboardBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun setBoard() {
        // 인텐트에서 제목 받아오기
        val noticeTitle = intent.getStringExtra("notice_title")

        // 받아온 제목을 화면에 설정
        binding.noticeboardTitleTv.text = noticeTitle
        // 날짜와 내용은 여기에 설정하는 부분이 필요함
        // binding.noticeboardDateTv.text = ""
        // binding.noticeboardContentTv.text = ""
    }
}