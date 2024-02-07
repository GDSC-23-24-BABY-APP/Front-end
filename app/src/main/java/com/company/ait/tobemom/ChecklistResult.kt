package com.company.ait.tobemom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckedTextView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChecklistResult : AppCompatActivity() {

    private lateinit var dateTextView: TextView
    private lateinit var resultCheck: TextView
    private lateinit var btnSubmitResult: AppCompatButton
    private lateinit var btnHospital : AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist_result)

        dateTextView = findViewById(R.id.dateTextView)
        resultCheck = findViewById(R.id.resultOfCheck)
        btnSubmitResult = findViewById(R.id.btnSubmitResult)
        btnHospital = findViewById(R.id.btnHospital)

        //날짜 표시
        displayCurrentDate()

        //체크리스트 결과 내용 불러오기
        getChecklistResult()

        //btnSubmitResult 버튼 클릭시 다른 화면으로 이동
        btnSubmitResult.setOnClickListener {
            val intent = Intent(this, CheckHealth::class.java)
            startActivity(intent)
        }

        //btnHospital 버튼 클릭 시 지도 화면으로 이동
        btnHospital.setOnClickListener {
            val intent = Intent(this,HospitalMap::class.java)
            startActivity(intent)
        }
    }

    private fun displayCurrentDate(){
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        dateTextView.text = currentDate
    }
    private fun getChecklistResult(){
        val checklistResult ="체크리스트 결과 내용"
        resultCheck.text = checklistResult
    }
}