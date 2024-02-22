package com.company.ait.tobemom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChangeBabyRVAdapter(private val babyList: List<Baby>) : RecyclerView.Adapter<ChangeBabyRVAdapter.BabyViewHolder>() {

    // 초기에 첫 번째 아이템을 선택한 것으로 설정
    private var selectedBabyPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BabyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_baby_list, parent, false)
        return BabyViewHolder(view)
    }

    override fun onBindViewHolder(holder: BabyViewHolder, position: Int) {
        val baby = babyList[position]
        holder.bind(baby)

        // 선택된 아기에 대한 표시 여부 확인
        if (position == selectedBabyPosition) {
            holder.selectBaby()
        } else {
            holder.deselectBaby()
        }

        // 아기가 클릭되었을 때
        holder.itemView.setOnClickListener {
            // 이전에 선택된 아기의 선택 상태를 해제
            val previouslySelectedPosition = selectedBabyPosition
            selectedBabyPosition = holder.adapterPosition

            // 이전에 선택된 아기의 선택 상태를 해제하고 새로 선택된 아기의 선택 상태를 갱신
            notifyItemChanged(previouslySelectedPosition)
            notifyItemChanged(selectedBabyPosition)
        }
    }

    override fun getItemCount(): Int {
        return babyList.size
    }

    inner class BabyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.item_birthname_tv)
        private val selectedTextView: TextView = itemView.findViewById(R.id.item_seleced_tv)
        private val backgroundLayout: LinearLayout = itemView.findViewById(R.id.item_background_ll)

        fun bind(baby: Baby) {
            nameTextView.text = baby.name
        }

        fun selectBaby() {
            selectedTextView.visibility = View.VISIBLE
            backgroundLayout.setBackgroundResource(R.drawable.background_maincolor)
        }

        fun deselectBaby() {
            selectedTextView.visibility = View.GONE
            backgroundLayout.setBackgroundResource(R.drawable.background_subcolor)
        }
    }
}
