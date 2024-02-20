package com.company.ait.tobemom

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivityMypageSettingsAddbabyBinding
import com.company.ait.tobemom.utils.RetrofitClient2
import com.company.ait.tobemom.utils.RetrofitObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date

class AddbabyActivity : AppCompatActivity() {

    lateinit var binding: ActivityMypageSettingsAddbabyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageSettingsAddbabyBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //뒤로가기
        goBack()
        //태명, 출산예정일 저장
        val birthName: String = binding.addbabyBirthnameEt.text.toString()
        val birthDate: Date = SimpleDateFormat("yyyy-MM-dd").parse(binding.addbabyBirthdateEt.text.toString())
        addBabyExistence(birthName, birthDate)
        //저장 버튼 클릭 이벤트 처리
        saveBtn()
        //link 화면으로 초대코드 전달
        //addToLink()
    }

    private fun goBack() {
        binding.addbabyBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun saveBtn() {
        binding.addbabyAddBtn.setOnClickListener {
            Toast.makeText(this, "The baby information has been registered.", Toast.LENGTH_SHORT).show()
            //백으로 아기 정보 넘겨주기

            finish()
        }
    }

    private fun addToLink(responseBody: String) {
        val jsonObject = JSONObject(responseBody)
        val babyId = jsonObject.getInt("babyId")

        // Intent를 통해 MyPageLinkActivity에 데이터 전달
        val intent = Intent(this, MyPageLinkActivity::class.java).apply {
            putExtra("babyId", babyId)
        }
        startActivity(intent)
    }

    private fun addBabyExistence(babyName: String, babyBirthDate: Date) : Boolean {
        //백이랑 연결
        val call: Call<String> = RetrofitObject.getRetrofitService.babyAdd(
            RetrofitClient2.BabyInfoRequest(
                babyName = babyName,
                babyBirthDate = babyBirthDate
            )
        )
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    val responseBody: String? = response.body()
                    if (responseBody != null) {
                        // responseBody가 존재하면 아기 정보를 백으로 넘기고 처리
                        sendBabyInfoToBackend(responseBody, babyName, babyBirthDate)
                    } else {
                        // responseBody가 null이면 이미 아기가 존재한다는 메시지 표시
                        Toast.makeText(this@AddbabyActivity, "The baby already exists.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // 응답이 실패한 경우에 대한 처리
                    Toast.makeText(this@AddbabyActivity, "Failed to add baby. Please try again.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                // 네트워크 오류 등 호출 실패에 대한 처리
                Toast.makeText(this@AddbabyActivity, "Network error. Please check your internet connection.", Toast.LENGTH_SHORT).show()
            }
        })

        return false
    }

    // 아기 정보를 백으로 넘기는 함수
    private fun sendBabyInfoToBackend(responseBody: String, babyName: String, babyBirthDate: Date) {
        // 여기에 아기 정보를 백으로 넘기는 코드를 작성
        val jsonObject = JSONObject(responseBody)
        val babyId = jsonObject.getInt("babyId")

        // 예시로써, 아기 정보를 백으로 넘기는 코드를 작성합니다.
        val requestBody = RetrofitClient2.BabyInfoRequest(babyName, babyBirthDate)
        val call: Call<String> = RetrofitObject.getRetrofitService.babyAdd(requestBody)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    // 아기 정보가 성공적으로 백에 전송됨
                    Log.d("Babyinfo", "아기 정보 전송 성공")
                } else {
                    // 아기 정보 전송 실패
                    Toast.makeText(this@AddbabyActivity, "Failed to submit baby information.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                // 아기 정보 전송 실패
                Toast.makeText(this@AddbabyActivity, "Failed to submit baby information. Please try again.", Toast.LENGTH_SHORT).show()
                Log.e("Babyadd", "에러: ${t.message}")
            }
        })

    }
}