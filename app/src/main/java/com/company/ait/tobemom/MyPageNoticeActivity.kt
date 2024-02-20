package com.company.ait.tobemom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivityMypageNoticeBinding

class MyPageNoticeActivity : AppCompatActivity() {

    lateinit var binding: ActivityMypageNoticeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageNoticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //뒤로 가기
        goBack()
        //공지사항 상세 페이지로 이동
        goBoard()
    }

    private fun goBack() {
        binding.noticeBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun goBoard() {
        // 각각의 버튼을 클릭했을 때 제목을 MyPageNoticeboardActivity로 전달
        binding.noticeNotice1Btn.setOnClickListener {
            val intent = Intent(this, MyPageNoticeboardActivity::class.java)
            intent.putExtra("notice_title1", "App update notification (2024/01/04)")
            startActivity(intent)
        }

        binding.noticeNotice2Btn.setOnClickListener {
            val intent = Intent(this, MyPageNoticeboardActivity::class.java)
            intent.putExtra("notice_title2", "Launch of Share via Link service")
            startActivity(intent)
        }

        binding.noticeNotice3Btn.setOnClickListener {
            val intent = Intent(this, MyPageNoticeboardActivity::class.java)
            intent.putExtra("notice_title3", "Announcement of [DR.BARD] service launch")
            startActivity(intent)
        }

        binding.noticeNotice4Btn.setOnClickListener {
            val intent = Intent(this, MyPageNoticeboardActivity::class.java)
            intent.putExtra("notice_title4", "App error troubleshooting guide (2023/12/16)")
            startActivity(intent)
        }

        binding.noticeNotice5Btn.setOnClickListener {
            val intent = Intent(this, MyPageNoticeboardActivity::class.java)
            intent.putExtra("notice_title5", "Emotional Diary Service Launch Announcement")
            startActivity(intent)
        }
    }
}