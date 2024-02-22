package com.company.ait.tobemom
// CalendarAdapter.kt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CheckCalendarAdapter(private val dataList: List<CalendarItem>, private val clickListener: (String) -> Unit) :
    RecyclerView.Adapter<CheckCalendarAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_calendar, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.dateTextView.text = item.date

        // 아이템이 클릭되었을 때 해당 날짜에 대한 데이터 가져오기
        holder.itemView.setOnClickListener {
            clickListener.invoke(item.date)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}