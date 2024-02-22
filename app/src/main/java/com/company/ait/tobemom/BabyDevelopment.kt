package com.company.ait.tobemom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import org.w3c.dom.Text

class BabyDevelopment : Fragment() {

    private lateinit var babyDevelopBackBtn: ImageButton
    private lateinit var weekOfBabyTextView: TextView
    private lateinit var weekBabyDescriptionTextView : TextView
    private lateinit var previousWeekBtn: ImageButton
    private lateinit var nextWeekBtn: ImageButton
    private lateinit var bdVolumeBtn: ImageButton

    private var currentWeekIndex: Int = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_baby_development, container, false)

        babyDevelopBackBtn = view.findViewById(R.id.BabyDevelop_back_btn)
        weekOfBabyTextView = view.findViewById(R.id.week_of_baby) //Title
        weekBabyDescriptionTextView = view.findViewById(R.id.week_baby_description) //Content
        previousWeekBtn = view.findViewById(R.id.previous_week_button)
        nextWeekBtn = view.findViewById(R.id.next_week_button)
        bdVolumeBtn = view.findViewById(R.id.bd_btn_volume)

        //초기 값 설정
        updateUIForCurrentWeek()

        //Text to Speech
        bdVolumeBtn.setOnClickListener {
            //TODO BackEnd 연결 필요
        }

        babyDevelopBackBtn.setOnClickListener{
            navigateToOtherFragment()
        }

        //버튼에 클릭 리스너 설정
        previousWeekBtn.setOnClickListener {
            //이전 주 버튼을 처리하는 로직 추가 (필요시)
            if(currentWeekIndex > 1){
                currentWeekIndex--
                updateUIForCurrentWeek()
            }
        }

        nextWeekBtn.setOnClickListener {
            // 다음 주 버튼을 처리하는 로직 호출
            if(currentWeekIndex < 8){
                currentWeekIndex++
                updateUIForCurrentWeek()
            }
        }
        return view
    }

    // 다른 Fragment로 이동하는 함수
    private fun navigateToOtherFragment() {
        // 이동하고 싶은 Fragment를 생성
        val newsFragment = NewsFragment()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()

        // 다른 Fragment로 교체하고 back stack에 추가
        transaction.replace(R.id.babyDvFragment, newsFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    private fun updateUIForCurrentWeek(){
        // 임신 시기에 따라 UI 업데이트
        when (currentWeekIndex) {
            1 -> {
                weekOfBabyTextView.text = getString(R.string.BDweek0_5)
                weekBabyDescriptionTextView.text = getString(R.string.BDweek0_5_content)
            }
            2 -> {
                weekOfBabyTextView.text = getString(R.string.BDweek6_10)
                weekBabyDescriptionTextView.text = getString(R.string.BDweek6_10_content)
            }
            3 -> {
                weekOfBabyTextView.text = getString(R.string.BDweek11_15)
                weekBabyDescriptionTextView.text = getString(R.string.BDweek11_15_content)
            }
            4 -> {
                weekOfBabyTextView.text = getString(R.string.BDweek16_20)
                weekBabyDescriptionTextView.text = getString(R.string.BDweek16_20_content)
            }
            5 -> {
                weekOfBabyTextView.text = getString(R.string.BDweek21_25)
                weekBabyDescriptionTextView.text = getString(R.string.BDweek21_25_content)
            }
            6 -> {
                weekOfBabyTextView.text = getString(R.string.BDweek26_30)
                weekBabyDescriptionTextView.text = getString(R.string.BDweek26_30_content)
            }
            7 -> {
                weekOfBabyTextView.text = getString(R.string.BDweek31_35)
                weekBabyDescriptionTextView.text = getString(R.string.BDweek31_35_content)
            }
            8 -> {
                weekOfBabyTextView.text = getString(R.string.BDweek36_40)
                weekBabyDescriptionTextView.text = getString(R.string.BDweek36_40_content)
            }
        }

    }
}