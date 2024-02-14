package com.company.ait.tobemom

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckedTextView
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChecklistResult : AppCompatActivity() {

    private lateinit var checkResultBackBtn: ImageButton
    private lateinit var dateTextView: TextView
    private lateinit var resultCheck: TextView
    private lateinit var btnSubmitResult: AppCompatButton
    private lateinit var btnHospital : AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist_result)

        checkResultBackBtn = findViewById(R.id.CheckResult_back_btn)
        dateTextView = findViewById(R.id.dateTextView)
        resultCheck = findViewById(R.id.resultOfCheck)
        btnSubmitResult = findViewById(R.id.btnSubmitResult)
        btnHospital = findViewById(R.id.btnHospital)

        //날짜 표시
        displayCurrentDate()

        //체크리스트 결과 내용 불러오기
        getChecklistResult()

        //Fragment를 Activity에 추가
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()

        checkResultBackBtn.setOnClickListener{
            val checklistFragment = Checklist()

            transaction.replace(R.id.checklist_fragment, checklistFragment)
            transaction.addToBackStack(null)
            transaction.commit()

        }

        //btnSubmitResult 버튼 클릭시 다른 화면으로 이동
        btnSubmitResult.setOnClickListener {
            val intent = Intent(this, CheckHealth::class.java)
            startActivity(intent)
        }

        //btnHospital 버튼 클릭 시 지도 화면으로 이동
        btnHospital.setOnClickListener {
            val hospitalMapFragment = HospitalMap()

            transaction.replace(R.id.hospitalMap_fragment, hospitalMapFragment)
            transaction.addToBackStack(null)
            transaction.commit()
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