package com.company.ait.tobemom

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract.CalendarAlerts
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CheckFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var calendarAdapter: CalendarAdapter
    private lateinit var checkHealthBtn : ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_check, container, false)

        checkHealthBtn.setOnClickListener{
            val intent = Intent(requireContext(), CheckHealth::class.java)
            startActivity(intent)
        }

        recyclerView = view.findViewById(R.id.recyclerViewCalendar)

        // RecyclerView에 레이아웃 매니저 설정
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            layoutManager.orientation
        )
        recyclerView.addItemDecoration(dividerItemDecoration)

        val startDate = Calendar.getInstance()
        val endDate = Calendar.getInstance()
        endDate.add(Calendar.DAY_OF_MONTH,280)

        // RecyclerView 어댑터 설정
        calendarAdapter = CalendarAdapter(generateDateList(startDate, endDate))
        recyclerView.adapter = calendarAdapter

        return view
    }

    private fun generateDateList(startDate: Calendar, endDate: Calendar): List<CalendarItem> {
        val dateList = mutableListOf<CalendarItem>()
        val currentDate = startDate.clone() as Calendar

        while (currentDate <= endDate) {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            dateList.add(CalendarItem(dateFormat.format(currentDate.time)))
            currentDate.add(Calendar.DAY_OF_MONTH, 1)
        }

        return dateList
    }
}
