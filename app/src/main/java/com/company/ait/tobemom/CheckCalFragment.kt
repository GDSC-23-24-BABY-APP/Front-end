package com.company.ait.tobemom

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.company.ait.tobemom.utils.RetrofitClient2
import com.company.ait.tobemom.utils.RetrofitObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CheckCalFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var checkAdapter: CheckCalendarAdapter
    private lateinit var checkHealthBtn : ImageButton

    // 저장할 context 변수 선언
    private lateinit var mContext: Context
    private lateinit var token: String // token 변수 추가
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // context 저장
        mContext = context
        token =getCurrentToken(mContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_check_cal, container, false)

        checkHealthBtn = view.findViewById(R.id.checklist_cal_btn)
        recyclerView = view.findViewById(R.id.recyclerViewCalendar)

        checkHealthBtn.setOnClickListener{
            val intent = Intent(requireContext(), CheckHealth::class.java)
            startActivity(intent)
        }

        val startDate = Calendar.getInstance()
        startDate.set(2024, Calendar.FEBRUARY, 1) //시작일을 "2024-02-01"로 설정
        val endDate = Calendar.getInstance()
        endDate.add(Calendar.DAY_OF_MONTH,300)

        // RecyclerView 어댑터 설정
        checkAdapter = CheckCalendarAdapter(generateDateList(startDate, endDate)) { clickedDate ->
            mappingDate(token, clickedDate)
        }
        recyclerView.layoutManager = LinearLayoutManager(mContext)
        recyclerView.adapter = checkAdapter

        return view
    }
    private var clickedDate: String=""

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

    private fun mappingDate(token: String, date: String) {
        clickedDate = date

        val checkCal = RetrofitObject.getRetrofitService
        val call = checkCal.getHealthList("Bearer $token")

        call.enqueue(object : Callback<RetrofitClient2.HealthListResponse> {
            override fun onResponse(
                call: Call<RetrofitClient2.HealthListResponse>,
                response: Response<RetrofitClient2.HealthListResponse>
            ) {
                if (response.isSuccessful) {
                    val healthListResponse = response.body()
                    // Log the response data
                    Log.d("CheckCalFragment", "Response Data: $healthListResponse")

                    // healthListResponse가 null이거나 data가 비어있는 경우 처리
                    if (healthListResponse == null || healthListResponse.data.isNullOrEmpty()) {
                        // Toast 메시지 표시
                        Toast.makeText(requireContext(), "There is no health record for the selected date.", Toast.LENGTH_SHORT).show()
                        // CheckHealth.kt 화면으로 이동
                        val intent = Intent(requireContext(), CheckHealth::class.java)
                        startActivity(intent)
                        return
                    }
                    // healthListResponse를 사용하여 날짜와 데이터를 매핑하고 처리
                    val healthDataForDate: List<RetrofitClient2.HealthData> =
                        healthListResponse.data.filter { it.createdDate == date }

                    // 데이터가 비어있는 경우 처리
                    if (healthDataForDate.isEmpty()) {
                        // Toast 메시지 표시
                        Toast.makeText(requireContext(), "There is no health record for the selected date.", Toast.LENGTH_SHORT).show()
                        // CheckHealth.kt 화면으로 이동
                        val intent = Intent(requireContext(), CheckHealth::class.java)
                        startActivity(intent)
                        return
                    }

                    // 데이터를 CheckHealthResult 액티비티로 전달
                    val intent = Intent(requireContext(), CheckHealthResult::class.java)
                    intent.putExtra("selectedDate", clickedDate)
                    intent.putExtra("healthData", healthDataForDate.firstOrNull())
                    startActivity(intent)

                } else {
                    // 응답이 실패한 경우 처리
                    Log.e("CheckCalFragment", "응답 실패: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.HealthListResponse>, t: Throwable) {
                // 통신 실패 처리
                Log.e("CheckCalFragment", "통신 실패: ${t.message}")
            }
        })
    }
    private fun getCurrentToken(context: Context): String {
        val sharedPref = context.getSharedPreferences("TOBEMOM", Context.MODE_PRIVATE)
        val token = sharedPref.getString("token", null) ?: ""
        Log.d("TokenDebug", "Token value: $token")
        return token
    }
}