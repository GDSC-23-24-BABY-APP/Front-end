package com.company.ait.tobemom

import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Checklist : Fragment() {

    private lateinit var dateTextView: TextView
    private lateinit var checklistcalbtn: ImageButton
    private lateinit var noticeChecklist: TextView
    private lateinit var scrollView: RecyclerView
    private lateinit var numQues: LinearLayout
    private lateinit var resultButton: AppCompatButton

    private lateinit var questionList : List<String>

    companion object {
        fun newInstance(): Checklist {
            return Checklist()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_checklist, container, false)

        dateTextView = view.findViewById(R.id.dateTextView)
        checklistcalbtn = view.findViewById(R.id.checklist_cal_btn)
        noticeChecklist = view.findViewById(R.id.notice_checklist)
        scrollView = view.findViewById(R.id.scrollView)
        numQues = view.findViewById(R.id.numQues)
        resultButton = view.findViewById(R.id.resultBtn)

        //날짜 표시
        displayCurrentDate()

        //ImageButton 클릭 리스너 설정 (다른 화면으로 이동하는 기능)
        checklistcalbtn.setOnClickListener{
            val checkFragment = CheckFragment()

            //FragmentManager를 통해 트랜젝션 시작
            val fragmentManager = childFragmentManager
            val transaction = fragmentManager.beginTransaction()

            //R.id.fragment_container는 checkFragment가 표시할 레이아웃 컨네이너의 id
            transaction.replace(R.id.checkFragment, checkFragment)
            transaction.addToBackStack(null) //뒤로 가기 버튼을 눌렀을 대 이전 fragment로 이동
            transaction.commit()
        }

        //질문 동적으로 추가
        generateQuestionViews()

        //결과 버튼 클릭 리스너 설정
        resultButton.setOnClickListener {
            val intent = Intent(requireContext(), ChecklistResult::class.java)
            startActivity(intent)
        }

        return view

    }
    private fun displayCurrentDate(){
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        dateTextView.text = currentDate
    }
    private fun generateQuestionViews(){
        for (question in questionList){
            addQuestionView(question)
        }
    }
    private fun addQuestionView(question: String){
        val checkedTextView = CheckedTextView(requireContext())
        checkedTextView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        checkedTextView.text = question
        checkedTextView.gravity = Gravity.CENTER
        checkedTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
        checkedTextView.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.mainText
            )
        )
        checkedTextView.typeface = ResourcesCompat.getFont(requireContext(), R.font.leeseoyun)
        checkedTextView.checkMarkDrawable = ContextCompat.getDrawable(
            requireContext(),
            android.R.drawable.checkbox_off_background
        )
        checkedTextView.checkMarkTintList =
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.subColor
                )
            )
        // 동적으로 추가한 View의 구분선 추가
        val separatorView = View(requireContext())
        separatorView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            resources.dpToPx(2) // 2dp의 두께로 구분선 추가
        )
        separatorView.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.mainColor
            )
        )

        numQues.addView(checkedTextView)
        numQues.addView(separatorView)
    }

}
fun Resources.dpToPx(dp: Int): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        this.displayMetrics
    ).toInt()

}