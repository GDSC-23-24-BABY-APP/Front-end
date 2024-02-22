package com.company.ait.tobemom

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivityMypageSettingsCalendarsetBinding

class MyPageCalendarsetActivity : AppCompatActivity() {

    lateinit var binding: ActivityMypageSettingsCalendarsetBinding
    lateinit var startWeekname: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageSettingsCalendarsetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 라디오 그룹에서 라디오 버튼이 선택될 때 호출되는 리스너 설정
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            // 선택된 라디오 버튼의 id에 따라 startWeekname 변수 설정
            startWeekname = when (checkedId) {  //시작 요일 설정 저장
                R.id.rg_startsunday_btn -> "sunday"
                R.id.rg_startmonday_btn -> "monday"
                else -> "" // 다른 버튼이 선택된 경우 예외 처리
            }
        }

        //변경 버튼 클릭 이벤트 설정
        binding.calendarsetCheckBtn.setOnClickListener {
            //백에 저장하기

            Toast.makeText(this, "The calendar settings have been saved.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}