package com.company.ait.tobemom

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class CalendarFragment : Fragment() {

    private val TAG = javaClass.simpleName
    lateinit var mContext: Context

    var pageIndex = 0
    lateinit var currentDate: Date

    lateinit var calendarYearMonthText: TextView
    lateinit var calendarLayout: LinearLayout
    lateinit var calendarView: RecyclerView
    lateinit var calendarAdapter: CalendarAdapter

    companion object{
        var instance: CalendarFragment? = null
    }
    override fun onAttach(context: Context){
        super.onAttach(context)
        if (context is MainActivity){
            mContext = context
        }
    }
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        instance = this
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        initView(view)
        initCalendar()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
    fun initView(view: View){
        pageIndex -= (Int.MAX_VALUE/2)
        Log.e(TAG, "Calendar Index: $pageIndex")
        calendarYearMonthText = view.findViewById(R.id.calendar_year_month_text)
        calendarLayout = view.findViewById(R.id.calendar_layout)
        calendarView = view.findViewById(R.id.calendar_view)
        //날짜 적용
        val date = Calendar.getInstance().run{
            add(Calendar.MONTH,pageIndex)
            time
        }
        currentDate = date
        Log.e(TAG, "$date")
        //포맷 적용
        var datetime: String = SimpleDateFormat(
            mContext.getString(R.string.calendar_year_month_format),
            Locale.KOREA
        ).format(date.time)
        calendarYearMonthText.setText(datetime)
    }
    fun initCalendar() {
        // 각 월의 1일의 요일을 구해
        // 첫 주의 일요일~해당 요일 전까지는 ""으로
        // 말일까지 해당 날짜
        // 마지막 날짜 뒤로는 ""으로 처리하여
        // CalendarAdapter로 List를 넘김
        calendarAdapter = CalendarAdapter(mContext, calendarLayout, currentDate)
        calendarView.adapter = calendarAdapter
        calendarView.layoutManager = GridLayoutManager(mContext, 7, GridLayoutManager.VERTICAL, false)
        calendarView.setHasFixedSize(true)
        calendarAdapter.itemClick = object :
            CalendarAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val firstDateIndex = calendarAdapter.dataList.indexOf(1)
                val lastDateIndex = calendarAdapter.dataList.lastIndexOf(calendarAdapter.furangCalendar.currentMaxDate)
                // 현재 월의 1일 이전, 현재 월의 마지막일 이후는 터치 disable
                if (position < firstDateIndex || position > lastDateIndex) {
                    return
                }
                val day = calendarAdapter.dataList[position].toString()
                val date = "${calendarYearMonthText.text}${day}일"
                Log.d(TAG, "$date")
                //val mainTab = mActivity.main_bottom_menu
                //mainTab.setScrollPosition(1, 0f, true)
                //val mainViewPager = mActivity.main_pager
                //mainViewPager.currentItem = 1
                //RoutineDateLiveData.getInstance().getLiveProgress().value = date
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        instance = null
    }
}