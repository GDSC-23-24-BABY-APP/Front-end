package com.company.ait.tobemom

import android.os.Bundle
import android.provider.CalendarContract.CalendarAlerts
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CheckFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var calendarAdapter: CalendarAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_check, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewCalendar)

        // RecyclerView에 레이아웃 매니저 설정
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        // RecyclerView 어댑터 설정
        calendarAdapter = CalendarAdapter(generateDateList())
        recyclerView.adapter = calendarAdapter

        return view
    }

    private fun generateDateList(): List<CalendarItem> {
        val dateList = mutableListOf<CalendarItem>()
        val calendar = Calendar.getInstance()

        for (i in 1..31) {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            dateList.add(CalendarItem(dateFormat.format(calendar.time)))
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        return dateList
    }
}
