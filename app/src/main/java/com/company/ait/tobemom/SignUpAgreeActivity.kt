package com.company.ait.tobemom

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivitySignupAgreeBinding

class SignUpAgreeActivity: AppCompatActivity() {
    lateinit var binding: ActivitySignupAgreeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupAgreeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 초기 상태에서는 다음 버튼은 숨겨져 있어야 함
        binding.agreeNextOkBtn.visibility = View.GONE
        binding.agreeNextNoBtn.visibility = View.VISIBLE

        //뒤로가기
        goBack()

        //필수 약관 체크
        updateNextButtonVisibility()

        //버튼 클릭 이벤트 처리
        // 이미지 버튼들의 ID 리스트
        val buttonIds = listOf(R.id.agree_detail1_btn, R.id.agree_detail2_btn, R.id.agree_detail3_btn, R.id.agree_detail4_btn)

        // 각 이미지 버튼에 대해 클릭 이벤트를 설정
        for (buttonId in buttonIds) {
            val imageButton: ImageButton = findViewById(buttonId)
            imageButton.setOnClickListener {
                // 이미지 토글 함수 호출
                toggleImage(imageButton)

                // 필수 체크요소가 모두 클릭되었을 때 다음 버튼을 표시하고 회색 버튼을 숨김
                if (binding.agreeAgree1Btn.tag == true && binding.agreeAgree2Btn.tag == true && binding.agreeAgree3Btn.tag == true) {
                    binding.agreeNextOkBtn.visibility = View.VISIBLE
                    binding.agreeNextNoBtn.visibility = View.GONE
                } else {
                    binding.agreeNextOkBtn.visibility = View.GONE
                    binding.agreeNextNoBtn.visibility = View.VISIBLE
                }
            }
        }

        //전체 동의 클릭 이벤트 처리
        allAgreeBtn()

        // 각 약관보기 텍스트 클릭 시 해당 약관 내용을 보여주는 액티비티로 이동
        viewAgreeContent()

        //아이디, 비번 생성 화면으로 이동
        goNext()
    }

    private fun goBack() {
        binding.agreeBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun toggleImage(imageButton: ImageButton) {
        // 이미지 토글 함수
        // 이미지 버튼의 태그를 통해 현재 상태를 추적
        if (imageButton.tag == null || imageButton.tag == false) {
            // 태그가 null이거나 false일 경우, 체크 이미지로 변경하고 태그를 true로 설정
            imageButton.setImageResource(R.drawable.check_btn)
            imageButton.tag = true
        } else {
            // 태그가 true인 경우, 언체크 이미지로 변경하고 태그를 false로 설정
            imageButton.setImageResource(R.drawable.ic_nocheck)
            imageButton.tag = false
        }
    }

    private fun updateNextButtonVisibility() {
        // 모든 이미지 버튼들의 체크 상태 확인
        val isImageButton1Checked = binding.agreeDetail1Btn.tag as? Boolean ?: false
        val isImageButton2Checked = binding.agreeDetail2Btn.tag as? Boolean ?: false
        val isImageButton3Checked = binding.agreeDetail3Btn.tag as? Boolean ?: false

        // 모든 이미지 버튼이 체크되었을 때
        if (isImageButton1Checked && isImageButton2Checked && isImageButton3Checked) {
            // 다음 버튼 표시, 회색 버튼 숨김
            binding.agreeNextOkBtn.visibility = View.VISIBLE
            binding.agreeNextNoBtn.visibility = View.GONE
        } else {
            // 그 외의 경우 다음 버튼 숨김, 회색 버튼 표시
            binding.agreeNextOkBtn.visibility = View.GONE
            binding.agreeNextNoBtn.visibility = View.VISIBLE
        }
    }

    private fun allAgreeBtn() {
        binding.agreeAllAgreeBtn.setOnClickListener {
            // 전체 동의 버튼이 클릭되었을 때
            val isChecked = binding.agreeAllAgreeBtn.tag as? Boolean ?: false

            // 전체 동의 이미지 버튼 변경
            if (!isChecked) {
                binding.agreeAllAgreeBtn.setImageResource(R.drawable.check_btn)
                binding.agreeAllAgreeBtn.tag = true
            } else {
                binding.agreeAllAgreeBtn.setImageResource(R.drawable.ic_nocheck)
                binding.agreeAllAgreeBtn.tag = false
            }

            // agree_detail1_btn, agree_detail2_btn, agree_detail3_btn, agree_detail4_btn 이미지 버튼 변경
            val imageButtonIds = listOf(R.id.agree_detail1_btn, R.id.agree_detail2_btn, R.id.agree_detail3_btn, R.id.agree_detail4_btn)
            for (buttonId in imageButtonIds) {
                val imageButton: ImageButton = findViewById(buttonId)
                imageButton.setImageResource(if (!isChecked) R.drawable.check_btn else R.drawable.ic_nocheck)
                imageButton.tag = !isChecked
            }

            // 필수 체크요소가 모두 클릭되었을 때 다음 버튼을 표시하고 회색 버튼을 숨김
            updateNextButtonVisibility()
        }
    }



    private fun goNext() {
        binding.agreeNextOkBtn.setOnClickListener {
            val intent = Intent(this, SignUpMakeidpwActivity::class.java)
            startActivity(intent)
        }
    }

    private fun viewAgreeContent() {
        // 각 약관보기 텍스트 클릭 시 해당 약관 내용을 보여주는 액티비티로 이동
        binding.agreeAgree1Btn.setOnClickListener {
            navigateToTermsContent(getString(R.string.terms_of_service))
        }
        binding.agreeAgree2Btn.setOnClickListener {
            navigateToTermsContent(getString(R.string.privacy_policy))
        }
        binding.agreeAgree3Btn.setOnClickListener {
            navigateToTermsContent(getString(R.string.third_party_consent))
        }
        binding.agreeAgree4Btn.setOnClickListener {
            navigateToTermsContent(getString(R.string.collect_and_use))
        }
        binding.agreeAgree1Btn.setOnClickListener {
            navigateToTermsContent(getString(R.string.push_notification))
        }
    }

    private fun navigateToTermsContent(title: String) {
        val intent = Intent(this, TermsContentActivity::class.java)
        // 약관 타이틀을 Intent에 추가
        intent.putExtra(TermsContentActivity.EXTRA_CONTENT_TITLE, title)
        startActivity(intent)
    }
}