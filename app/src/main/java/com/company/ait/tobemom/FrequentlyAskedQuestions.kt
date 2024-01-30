package com.company.ait.tobemom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView

class FrequentlyAskedQuestions : Fragment() {

    private lateinit var questionContent: TextView
    private lateinit var previousWeekButton: ImageButton
    private lateinit var nextWeekButton: ImageButton
    private lateinit var firstPregnancyText: TextView

    private var currentWeek: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_frequently_asked_questions, container, false)

        questionContent = view.findViewById(R.id.question_content)
        previousWeekButton = view.findViewById(R.id.previous_week_button)
        nextWeekButton = view.findViewById(R.id.next_week_button)
        firstPregnancyText = view.findViewById(R.id.first_pregnancy)

        //초기 값 설정
        updateUIForCurrentWeek()

        //버튼에 클릭 리스너 설정
        previousWeekButton.setOnClickListener {
            //이전 주 버튼을 처리하는 로직 추가 (필요시)
            if(currentWeek > 1){
                currentWeek--
                updateUIForCurrentWeek()
            }
        }

        nextWeekButton.setOnClickListener {
            // 다음 주 버튼을 처리하는 로직 호출
            if(currentWeek < 3){
                currentWeek++
                updateUIForCurrentWeek()
            }
        }
        return view
    }

    private fun updateUIForCurrentWeek(){
        // 임신 시기에 따라 UI 업데이트
        when (currentWeek) {
            1 -> {
                firstPregnancyText.text = "임신 초기 (~11주)"
                questionContent.text = "임신 초기 (~11주)에 대한 질문들"
            }
            2 -> {
                firstPregnancyText.text = "임신 중기 (12주~27주)"
                questionContent.text = "임신 중기 (12주~27주)에 대한 질문들"
            }
            3 -> {
                firstPregnancyText.text = "임신 후기 (27주~40주)"
                questionContent.text = "임신 후기 (27주~40주)에 대한 질문들"
            }
        }

    }

}