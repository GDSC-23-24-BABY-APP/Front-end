package com.company.ait.tobemom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView


class PregnancyTip : Fragment() {

    private lateinit var weekOfPregnancy: TextView
    private lateinit var pregnancyTips : TextView
    private lateinit var previousWeekBtn: ImageButton
    private lateinit var nextWeekBtn: ImageButton

    private lateinit var pregnancyTipContents: Array<String>
    private var currentWeekIndex: Int=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pregnancy_tip, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Initialize views
        weekOfPregnancy = view.findViewById(R.id.week_of_pregnancy)
        pregnancyTips = view.findViewById(R.id.pregnancy_tips)
        previousWeekBtn = view.findViewById(R.id.previous_week_button)
        nextWeekBtn = view.findViewById(R.id.next_week_button)

        //Define baby development contents for each week
        pregnancyTipContents = arrayOf(
            "0~5주차 임신 팁",
            "5~10주차 임신 팁 내용",
            "11~15주차 임신 팁 내용"
            // ...(나머지 주차별 임신 팁 내용)
        )
        //set initial week and description
        currentWeekIndex = 0
        weekOfPregnancy.text = "0~5주차"
        pregnancyTips.text=pregnancyTipContents[currentWeekIndex]

        //set click listeners for previous and next week buttons
        previousWeekBtn.setOnClickListener{
            if (currentWeekIndex > 0){
                currentWeekIndex--
                weekOfPregnancy.text = getWeekRange(currentWeekIndex)
                pregnancyTips.text = pregnancyTipContents[currentWeekIndex]
            }
        }
        nextWeekBtn.setOnClickListener {
            if (currentWeekIndex < pregnancyTipContents.size - 1) {
                currentWeekIndex++
                weekOfPregnancy.text = getWeekRange(currentWeekIndex)
                pregnancyTips.text = pregnancyTipContents[currentWeekIndex]
            }
        }
    }
    private fun getWeekRange(weekIndex: Int): String {
        val startWeek = weekIndex * 5
        val endWeek = startWeek + 5
        return "$startWeek~$endWeek 주차"
    }
}
