package com.company.ait.tobemom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.company.ait.tobemom.utils.RetrofitClient2

class ChecklistAdapter(private var questionList: List<RetrofitClient2.Question>) :
    RecyclerView.Adapter<ChecklistAdapter.ViewHolder>() {
    // answerList를 관리할 리스트
    private var answerList: IntArray = IntArray(questionList.size)
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ckdTv1: CheckedTextView = itemView.findViewById(R.id.ckdTv1)
        //val dividerView: View = itemView.findViewById(R.id.dividerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_checklistques, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val question = questionList[position]

            // CheckedTextView에 데이터를 바인딩합니다.
            holder.ckdTv1.text = "Q${question.num}. ${question.content}"

            // answerList에 해당 질문의 체크 여부를 기준으로 값을 추가하거나 제거합니다.
            holder.ckdTv1.isChecked = answerList[position] == 1

            // 체크 이벤트를 처리합니다.
            holder.ckdTv1.setOnClickListener {
                // 현재 아이템의 체크 상태를 토글합니다.
                if (answerList[position] == 1) {
                    answerList[position] = 0
                } else {
                    answerList[position] = 1
                }
                // 상태가 변경되었음을 알리고 UI를 갱신합니다.
                notifyDataSetChanged()
            }

    }
    override fun getItemCount(): Int {
        return questionList.size
    }

    // 백엔드로 보낼 ChecklistResult 객체 생성
    fun getChecklistResult(): RetrofitClient2.ChecklistResultData {
        return RetrofitClient2.ChecklistResultData(
            checkListNum = 0,  // 원하는 값으로 설정
            answerList = answerList.toList()
        )
    }

    // 기존 setData 메서드는 변경하지 않았습니다.
    fun setData(newQuestionList: List<RetrofitClient2.Question>) {
        questionList = newQuestionList
        answerList = IntArray(questionList.size)
        notifyDataSetChanged()
    }
    }