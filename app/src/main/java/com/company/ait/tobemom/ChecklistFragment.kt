package com.company.ait.tobemom

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.company.ait.tobemom.utils.RetrofitAPI
import com.company.ait.tobemom.utils.RetrofitClient2
import com.company.ait.tobemom.utils.RetrofitObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChecklistFragment : Fragment() {

    private lateinit var dateTextView: TextView
    private lateinit var checklistcalbtn: ImageButton
    private lateinit var noticeChecklist: TextView
    private lateinit var checklistQuesListRv: RecyclerView
    private lateinit var resultButton: AppCompatButton
    private lateinit var checklistAdapter: ChecklistAdapter
    companion object {
        fun newInstance(): ChecklistFragment {
            return ChecklistFragment()
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
        checklistQuesListRv = view.findViewById(R.id.checklistques_list_rv)
        resultButton = view.findViewById(R.id.resultBtn)

        //날짜 표시
        displayCurrentDate()

        //ImageButton 클릭 리스너 설정 (다른 화면으로 이동하는 기능)
        checklistcalbtn.setOnClickListener{
            val checkCalFragment = CheckCalFragment()

            //FragmentManager를 통해 트랜젝션 시작
            val fragmentManager = childFragmentManager
            val transaction = fragmentManager.beginTransaction()

            //R.id.fragment_container는 checkFragment가 표시할 레이아웃 컨네이너의 id
            transaction.replace(R.id.checklist_fragment, checkCalFragment)
            transaction.addToBackStack(null) //뒤로 가기 버튼을 눌렀을 대 이전 fragment로 이동
            transaction.commit()
        }

        // RecyclerView 및 Adapter 설정
        // RecyclerView 및 Adapter 설정
        checklistAdapter = ChecklistAdapter(emptyList())
        checklistQuesListRv.layoutManager = LinearLayoutManager(requireContext())
        checklistQuesListRv.adapter = checklistAdapter



        //API 호출 및 데이터 처리
        getChecklistData()

        //결과 버튼 클릭 리스너 설정
        resultButton.setOnClickListener {
            // ChecklistAdapter에서 백엔드로 전송할 데이터 얻기
            val checklistResultData = checklistAdapter.getChecklistResult()
            // 여기에 얻은 데이터를 사용하여 백엔드로 전송하거나 원하는 동작을 수행
            sendChecklistResult(checklistResultData)

        }
        return view
    }
    private fun getChecklistData() {
        val retrofitAPI = RetrofitObject.getRetrofitService
        val call = retrofitAPI.getChecklist(0)

        call.enqueue(object : Callback<RetrofitClient2.ChecklistResponse> {
            override fun onResponse(
                call: Call<RetrofitClient2.ChecklistResponse>,
                response: Response<RetrofitClient2.ChecklistResponse>
            ) {
                if (response.isSuccessful) {
                    val checklistData = response.body()?.data
                    checklistData?.let {
                        // 데이터를 어댑터에 전달
                        checklistAdapter.setData(it.questions)
                    }
                } else {
                    // API 호출 실패 시 처리
                    // 예: Toast 메시지 출력 등
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.ChecklistResponse>, t: Throwable) {
                // API 호출 실패 시 처리
                // 예: Toast 메시지 출력 등
            }
        })
    }
    private fun sendChecklistResult(checklistResultData: RetrofitClient2.ChecklistResultData) {
        val retrofitAPI = RetrofitObject.getRetrofitService

        val call = retrofitAPI.sendChecklist(checklistResultData)
        call.enqueue(object : Callback<RetrofitClient2.ChecklistCalResultResponse> {
            override fun onResponse(
                call: Call<RetrofitClient2.ChecklistCalResultResponse>,
                response: Response<RetrofitClient2.ChecklistCalResultResponse>
            ) {
                if (response.isSuccessful) {
                    // 서버 응답이 성공한 경우, 서버 응답값을 사용하지 않고 처리


                    // 성공한 경우 데이터를 ChecklistResult 액티비티로 전달하면서 화면 전환
                    val resultData = response.body()?.data
                    if (resultData != null) {
                        // 성공한 경우 데이터를 ChecklistResult 액티비티로 전달하면서 화면 전환
                        val intent = Intent(requireContext(), ChecklistResult::class.java)
                        intent.putExtra("resultData", resultData)
                        Log.d("resultData", "값: ${resultData.toString()}")
                        startActivity(intent)
                    } else {
                        // resultData가 null인 경우 처리
                        Log.e(TAG, "Server response successful, but data is null.")
                    }
                } else {
                    // 서버 응답이 실패한 경우 처리
                    Log.e(TAG, "Server response failed. Code: ${response.code()}, Message: ${response.message()}")
                    // 예: Toast 메시지 출력 등
                }
            }

            override fun onFailure(
                call: Call<RetrofitClient2.ChecklistCalResultResponse>,
                t: Throwable
            ) {
                // API 호출 실패 시 처리
                // 예: Toast 메시지 출력 등
            }
        })
    }

    private fun displayCurrentDate(){
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        dateTextView.text = currentDate
    }
}
