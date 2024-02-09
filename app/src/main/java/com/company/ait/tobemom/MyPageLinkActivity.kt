package com.company.ait.tobemom

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivityMypageLinkBinding

class MyPageLinkActivity: AppCompatActivity() {

    lateinit var binding: ActivityMypageLinkBinding

    private val isDad: Boolean = false  //엄마인지 아빠인지 판별

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageLinkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //뒤로 가기
        goBack()

        if (!isDad) {  //아빠인 경우
            binding.linkEnterinvitecodeTv.visibility = View.GONE
            binding.linkEnterinvitecodeTv.visibility = View.GONE
            binding.linkEnterinvitecodeTv.visibility = View.VISIBLE
            binding.linkEnterinvitecodeTv.visibility = View.VISIBLE
            binding.linkEnterinvitecodeTv.visibility = View.VISIBLE
            binding.linkEnterinvitecodeTv.visibility = View.VISIBLE

            //초대코드 입력 값 받기


            //연결하기 버튼 클릭 이벤트
            linkBtn()
        } else {  //엄마인 경우
            binding.linkEnterinvitecodeTv.visibility = View.VISIBLE
            binding.linkEnterinvitecodeTv.visibility = View.VISIBLE
            binding.linkEnterinvitecodeTv.visibility = View.GONE
            binding.linkEnterinvitecodeTv.visibility = View.GONE
            binding.linkEnterinvitecodeTv.visibility = View.GONE
            binding.linkEnterinvitecodeTv.visibility = View.GONE

            //초대코드 값 설정 및 클릭 시 클립보드 복사 처리
            binding.linkInvitecodeTv.text = "11111111"  //추후 텍스트 수정
            binding.linkInvitecodeTv.setOnClickListener {
                val textToCopy = binding.linkInvitecodeTv.text.toString()
                copyToClipboard(textToCopy)
            }
        }
    }

    private fun goBack() {
        binding.linkBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun copyToClipboard(text: String) {
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("Invite Text", text)
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(this, "초대 코드가 복사되었습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun linkBtn() {
        binding.linkLinkBtn.setOnClickListener {
            //연결하기 버튼 클릭 시 코드 작성
        }
    }
}