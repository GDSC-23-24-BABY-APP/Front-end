package com.company.ait.tobemom

import android.content.ContentValues.TAG
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.ait.tobemom.utils.RetrofitClient2
import com.company.ait.tobemom.utils.RetrofitObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChecklistResult : AppCompatActivity() {

    private lateinit var checkResultBackBtn: ImageButton
    private lateinit var dateTextView: TextView
    private lateinit var resultCheck: TextView
    private lateinit var symptomsTv: TextView
    private lateinit var btnSubmitResult: AppCompatButton
    private lateinit var btnHospital: AppCompatButton
    private lateinit var checklistAdapter: ChecklistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist_result)

        checkResultBackBtn = findViewById(R.id.CheckResult_back_btn)
        dateTextView = findViewById(R.id.dateTextView)
        resultCheck = findViewById(R.id.resultOfCheck)
        symptomsTv = findViewById(R.id.symptomTv)
        btnSubmitResult = findViewById(R.id.btnSubmitResult)
        btnHospital = findViewById(R.id.btnHospital)

        //날짜 표시
        displayCurrentDate()

        checklistAdapter = ChecklistAdapter(emptyList())

        //Fragment를 Activity에 추가
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()

        checkResultBackBtn.setOnClickListener {
            val checklistFragment = ChecklistFragment()

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


        // 인텐트에서 ResultData 객체 추출
        val resultData: RetrofitClient2.ResultData? = intent.getParcelableExtra("resultData")
        Log.d("resultData", "${resultData.toString()}")

        // ResultData 객체가 null이 아닌 경우에만 값들을 가져와 사용
        resultData?.let {
            val result: Int = it.result
            val checkedQ: List<String> = it.checkedQ

            // 이제 result 및 checkedQ 값을 사용할 수 있음
            // 예: TextView 등에 표시
            displayResults(result, checkedQ)
        }
    }

    private fun displayResults(result: Int, checkedQ: List<String>) {
        // 결과를 TextView 등에 표시하는 코드 작성
        when (result) {
            0 -> resultCheck.text = "체크리스트를 분석해본 결과 \n\n 현재 산모는 건강한 상태입니다."
            1 -> resultCheck.text = "체크리스트를 분석해본 결과 \n\n 현재 유산 위험이 의심됩니다."
            2 -> resultCheck.text = "체크리스트를 분석해본 결과 \n\n 현재 유산의 확률이 높습니다.\n가까운 산부인과나 평소 다니는 산부인과에 방문해보세요."
            3 -> resultCheck.text = "체크리스트를 분석해본 결과 \n\n 현재 사산 위험이 의심됩니다."
            4 -> resultCheck.text = "체크리스트를 분석해본 결과 \n\n 현재 사산의 확률이 높습니다.\n가까운 산부인과나 평소 다니는 산부인과에 방문해보세요."
        }

        // checkedQ를 활용하여 증상을 화면에 표시
        val symptomsText = "체크리스트를 기반으로 현재 산모가 가지고 있는 증상들을 \n알려드리겠습니다.\n"
        val symptomsList = checkedQ.joinToString("\n")

        // symptomsText를 TextView에 설정
        symptomsTv.text = "$symptomsText\n$symptomsList"
    }


    private fun displayCurrentDate() {
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        dateTextView.text = currentDate
    }

}