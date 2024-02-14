package com.company.ait.tobemom

import android.icu.util.Calendar
import java.util.Date

class FurangCalendar(date: Date){
    companion object{
        const val DAYS_OF_WEEK = 7
        const val LOW_OF_CALENDAR = 6
    }
    val calendar = Calendar.getInstance()

    var preTail = 0
    var nextHead = 0
    var currentMaxDate = 0

    var dateList = arrayListOf<Int>()

    init {
        calendar.time = date
    }
    fun initBaseCalendar(){
        makeMonthDate()
    }
    private fun makeMonthDate(){
        dateList.clear()

        calendar.set(Calendar.DATE, 1)

        currentMaxDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        preTail = calendar.get(Calendar.DAY_OF_WEEK) - 1

        makePrevTail(calendar.clone() as Calendar)
        makeCurrentMonth(calendar)

        nextHead = LOW_OF_CALENDAR * DAYS_OF_WEEK - (preTail + currentMaxDate)
        makeNextHead()
    }
    private fun makePrevTail(calendar: Calendar){
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1)
        val maxDate = calendar.getActualMaximum(Calendar.DATE)
        var maxOffsetDate = maxDate - preTail

        for( i in 1..preTail) dateList.add(++maxOffsetDate)
    }
    private fun makeCurrentMonth(calendar: Calendar){
        for(i in 1..calendar.getActualMaximum(Calendar.DATE)) dateList.add(i)
    }
    private fun makeNextHead(){
        var date = 1

        for(i in 1..nextHead) dateList.add(date++)
    }
}