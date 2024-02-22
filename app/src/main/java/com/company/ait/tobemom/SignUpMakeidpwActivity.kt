package com.company.ait.tobemom

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivitySignupMakeidpwBinding
import com.company.ait.tobemom.utils.RetrofitClient2
import com.company.ait.tobemom.utils.RetrofitObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpMakeidpwActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupMakeidpwBinding
    private lateinit var spinner_agency : Spinner
    private lateinit var spinner_family: Spinner

    private lateinit var id: String
    private lateinit var pw: String
    private lateinit var username: String
    private lateinit var babyName: String
    private lateinit var babyBirthDate: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupMakeidpwBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //통신사 및 가족 스피너
        agencySpinner()
        familySpinner()
        //뒤로가기
        goBack()
        //다음 버튼
        goNext()
    }

    private fun agencySpinner() {
        spinner_agency = findViewById<Spinner>(R.id.idpw_agency_sp)
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.스피너목록, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);

        ArrayAdapter.createFromResource(this, R.array.network_agency_array, R.layout.spinner_agency
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_agency.adapter = adapter
        }
        Log.d("SignUpMakeidpwActivity", "스피너 불러오기 성공")
    }

    private fun familySpinner() {
        spinner_family = findViewById(R.id.idpw_selectmember_sp)

        ArrayAdapter.createFromResource(this, R.array.family_array, R.layout.spinner_family
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_family.adapter = adapter
        }
        Log.d("SignUpMakeidpwActivity", "스피너 불러오기 성공")
    }

    private fun goBack() {
        binding.idpwBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun goNext() {
        //텍스트 저장
        username = binding.idpwNameEt.text.toString()
        id = binding.idpwMakeidEt.text.toString()
        pw = binding.idpwMakepwEt.text.toString()
        babyName = binding.idpwBirthnameEt.text.toString()
        babyBirthDate = binding.idpwBirthdayEt.text.toString()

        binding.idpwNextBtn.setOnClickListener {
            val signupRequest = RetrofitClient2.SignupRequest(id, pw, username, babyName, babyBirthDate)
            val call = RetrofitObject.getRetrofitService.signup(signupRequest)
            call.enqueue(object : Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if (response.isSuccessful) {
                        val signupData = response.body()
                        Log.d("SignUp", "${signupData.toString()}")
                        // API 호출이 성공적으로 완료되었을 때 수행할 작업을 정의하세요
                        startMainActivity()
                    } else {
                        // API 호출이 실패했을 때 수행할 작업을 정의하세요
                        Log.e("SignUp", response.errorBody()?.string() ?: "API 호출 실패")
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    // 네트워크 오류 등의 이유로 API 호출이 실패했을 때 수행할 작업을 정의하세요
                    Log.e("CheckHealth", "네트워크 오류 등의 이유로 API 호출 실패: ${t.message}")
                }
            })
        }
    }

    private fun startMainActivity() {
        // MainActivity 시작
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // 뒤로 가기 버튼으로 SignUpMakeidpwActivity로 돌아가지 않도록 SignUpMakeidpwActivity 종료
    }
}