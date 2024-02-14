package com.company.ait.tobemom

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivitySignupMakeidpwBinding
import com.company.ait.tobemom.utils.RetrofitClient2
import com.company.ait.tobemom.utils.RetrofitObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date

class SignUpMakeidpwActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupMakeidpwBinding
    private lateinit var spinner_agency : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupMakeidpwBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("SignUpMakeidpwActivity", "회원가입 진입 성공")

        //통신사 스피너
        agencySpinner()
        Log.d("SignUpMakeidpwActivity", "스피너 활성화 성공")

        //뒤로가기
        goBack()
        Log.d("SignUpMakeidpwActivity", "뒤로가기 버튼 활성화 성공")
        //다음 버튼
        goNext()
        Log.d("SignUpMakeidpwActivity", "다음 버튼 활성화 성공")

        // 회원가입 버튼 클릭 시 회원가입 처리를 수행합니다.
        idpwSignUp()
        Log.d("SignUpMakeidpwActivity", "회원가입 버튼 활성화 성공")

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

    private fun goBack() {
        binding.idpwBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun goNext() {
        binding.idpwNextBtn.setOnClickListener {
            //백엔드에 사용자 정보 저장
            val nickname: String = binding.idpwNameEt.text.toString()
            val id: String = binding.idpwMakeidEt.text.toString()
            Log.d("SignUpMakeidpwActivity", "id값: $id")
            val pw: String = binding.idpwMakepwEt.text.toString()
            Log.d("SignUpMakeidpwActivity", "pw걊: $pw")
            val pwcheck: String = binding.idpwCheckpwEt.text.toString()
            val phonenum: String = binding.idpwPhoneEt.text.toString()
            val birthname: String = binding.idpwBirthnameEt.text.toString()
            val birthday: String = binding.idpwBirthdayEt.text.toString()
            Log.d("SignUpMakeidpwActivity", "birthdate값: $birthday")
            var birthdate: Date = Date()
            Log.d("SignUpMakeidpwActivity", "사용자 정보 저장 성공")
            Log.d("SignUpMakeidpwActivity", "birthdate값: $birthdate")

            // 생년월일 문자열을 Date 객체로 변환
            if (birthday.isNotEmpty()) {
                birthdate = SimpleDateFormat("yyyy-MM-dd").parse(birthday)
                Log.d("SignUpMakeidpwActivity", "birthdate값: $birthdate")
            } else {
                // 사용자가 날짜를 입력하지 않은 경우에 대한 처리
                // 이 경우에는 birthdate를 null로 설정합니다.
                Toast.makeText(this, "생년월일을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            Log.d("SignUpMakeidpwActivity", "중간 성공")
            // saveUserInfo 함수 호출은 사용자가 날짜를 입력했을 때에 한해서 호출되어야 합니다.
            if (birthdate != null) {
                binding.idpwNextBtn.setOnClickListener {
                    saveUserinfo(nickname, id, pw, phonenum, birthname, birthdate)
                    Log.d("SignUpMakeidpwActivity", "서버에 전송 성공")
                }
            }

            // 사용자 정보 저장 함수 호출
            saveUserinfo(nickname, id, pw, phonenum, birthname, birthdate)

            val intent = Intent(this, SignUpFinActivity::class.java)
            startActivity(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getUser(): RetrofitClient2.SignupRequest {
        val id: String = binding.idpwMakeidEt.text.toString()
        val pw: String = binding.idpwMakepwEt.text.toString()
        val nickname: String = binding.idpwNameEt.text.toString()
        val phonenum: String = binding.idpwPhoneEt.text.toString()
        val birthname: String = binding.idpwBirthnameEt.text.toString()
        val birthday: String = binding.idpwBirthdayEt.text.toString()
        var birthdate: Date? = null

        // 생년월일 문자열을 Date 객체로 변환
        if (birthday.isNotEmpty()) {
            birthdate = SimpleDateFormat("yyyy-MM-dd").parse(birthday)
        } else {
            // 사용자가 날짜를 입력하지 않은 경우에 대한 처리
            // 이 경우에는 birthdate를 null로 설정합니다.
            Toast.makeText(this, "생년월일을 입력해주세요.", Toast.LENGTH_SHORT).show()
        }

        return RetrofitClient2.SignupRequest(
            email = id,
            password = pw,
            nickname = nickname,
            phoneNumber = phonenum,
            familyType = "", // 이 부분은 직접 값 설정 필요
            babyName = birthname, // 이 부분은 직접 값 설정 필요
            babyBirthDate = birthdate ?: Date() // 널이 아닌 경우에만 Date를 전달하고, 널인 경우에는 기본값으로 현재 날짜를 전달합니다.
        )
        Log.d("SignUpMakeidpwActivity", "사용자 정보 저장 및 서버 전송 성공")
    }


    private fun idpwSignUp() {
        if (binding.idpwMakeidEt.text.toString().isEmpty()) {
            Toast.makeText(this, "아이디 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.idpwMakepwEt.text.toString() != binding.idpwCheckpwEt.text.toString()) {
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        // 사용자 정보를 가져옵니다.
        val user = getUser()
        Log.d("SignUpMakeidpwActivity", "사용자 정보 가져오기 성공")

        // Retrofit 서비스 인터페이스를 통해 백엔드에 회원가입 요청을 보냅니다.
        val call = RetrofitObject.getRetrofitService.signup(user)
        Log.d("SignUpMakeidpwActivity", "서버에 요청보내기 성공")

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // 회원가입이 성공하면 MainActivity로 이동합니다.
                    startLoginActivity()
                } else {
                    // 응답이 실패했을 때에 대한 처리
                    Log.d("SignUpMakeidpwActivity", "회원 가입에 실패했습니다.")
                    Toast.makeText(this@SignUpMakeidpwActivity, "회원 가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // 요청 자체가 실패했을 때에 대한 처리
                Log.e("SignUpMakeidpwActivity", "서버에 접속할 수 없습니다.", t)
                Toast.makeText(this@SignUpMakeidpwActivity, "서버에 접속할 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun startLoginActivity() {
        // MainActivity 시작
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // 뒤로 가기 버튼으로 SignUpMakeidpwActivity로 돌아가지 않도록 SignUpMakeidpwActivity 종료
    }

    @Synchronized
    private fun saveUserinfo(
        nickname: String,
        id: String,
        pw: String,
        phonenum: String,
        birthname: String,
        birthdate: Date
    ) {

        // SignupRequest 객체 생성
        val signupRequest = RetrofitClient2.SignupRequest(
            email = id,
            password = pw,
            nickname = nickname,
            phoneNumber = phonenum,
            familyType = "", // 이 부분은 직접 값 설정 필요
            babyName = birthname, // 이 부분은 직접 값 설정 필요
            babyBirthDate = birthdate
        )
        Log.d("SignUpMakeidpwActivity", "signupRequest 객체 생성 성공")

        RetrofitObject.getRetrofitService.signup(signupRequest)
        Log.d("SignUpMakeidpwActivity", "서버에 data 전송 성공")
    }
}