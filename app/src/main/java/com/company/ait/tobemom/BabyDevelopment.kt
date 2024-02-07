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

    private lateinit var weekOfBabyTextView: TextView
    private lateinit var weekBabyDescriptionTextView : TextView
    private lateinit var previousWeekBtn: ImageButton
    private lateinit var nextWeekBtn: ImageButton

    private lateinit var babyDevelopmentContents: Array<String>
    private var currentWeekIndex: Int=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_baby_development, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Initialize views
        weekOfBabyTextView = view.findViewById(R.id.week_of_baby)
        weekBabyDescriptionTextView = view.findViewById(R.id.week_baby_description)
        previousWeekBtn = view.findViewById(R.id.previous_week_button)
        nextWeekBtn = view.findViewById(R.id.next_week_button)

        //Define baby development contents for each week
        babyDevelopmentContents = arrayOf(
            "0~5주차 아기 발달 내용",
            "5~10주차 아기 발달 내용",
            "11~15주차 아기 발달 내용"
            // ...(나머지 주차별 아기 발달 내용)
        )
        //set initial week and description
        currentWeekIndex = 0
        weekOfBabyTextView.text = "0~5주차"
        weekBabyDescriptionTextView.text=babyDevelopmentContents[currentWeekIndex]

        //set click listeners for previous and next week buttons
        previousWeekBtn.setOnClickListener{
            if (currentWeekIndex > 0){
                currentWeekIndex--
                weekOfBabyTextView.text = getWeekRange(currentWeekIndex)
                weekBabyDescriptionTextView.text = babyDevelopmentContents[currentWeekIndex]
            }
        }
        nextWeekBtn.setOnClickListener {
            if (currentWeekIndex < babyDevelopmentContents.size - 1) {
                currentWeekIndex++
                weekOfBabyTextView.text = getWeekRange(currentWeekIndex)
                weekBabyDescriptionTextView.text = babyDevelopmentContents[currentWeekIndex]
            }
        }
    }
    private fun getWeekRange(weekIndex: Int): String {
        val startWeek = weekIndex * 5
        val endWeek = startWeek + 5
        return "$startWeek~$endWeek 주차"
    }
}