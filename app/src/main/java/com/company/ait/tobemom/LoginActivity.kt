package com.company.ait.tobemom

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivityLoginBinding
import com.company.ait.tobemom.utils.RetrofitClient2
import com.company.ait.tobemom.utils.RetrofitObject
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), LoginView {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //해시키 값 확인
//        val keyHash = Utility.getKeyHash(this)
//        Log.d("Hash", keyHash)

        binding.loginToSigninBtn.setOnClickListener {
            startActivity(Intent(this, SignUpAgreeActivity::class.java))
        }

        binding.loginLoginBtn.setOnClickListener {
            login()
        }

        //아이디 비번 찾기
        goFindid()
        goResetpw()

        // 카카오 로그인 정보 확인
        checkLoginInfo()

        // 카카오 로그인
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                when {
                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                        Toast.makeText(this, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
                    }

                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
                    }

                    error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT)
                            .show()
                    }

                    error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
                        Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                    }

                    error.toString() == AuthErrorCause.InvalidScope.toString() -> {
                        Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                    }

                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                        Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT)
                            .show()
                    }

                    error.toString() == AuthErrorCause.ServerError.toString() -> {
                        Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
                    }

                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                        Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                    }

                    else -> { // Unknown
                        Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
                    }
                }
            } else if (token != null) {
                Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                //로그인 했으니 여기서 이메일 이름을 받아옴
                UserApiClient.instance.me { user, error ->
                    if (error != null) {
                        Log.e("login", "사용자 정보 요청 실패", error)
                    } else if (user != null) {
                        Log.d("login", "사용자 정보 요청 성공")
                        val email = user.kakaoAccount?.email
                        val name = user.kakaoAccount?.profile?.nickname
                        //이메일 이름 보내서 서버와 연결
                        //loginServer(email!!, name!!)
                    }
                }

                val intent = Intent(this, SignUpAgreeActivity::class.java)
                startActivity(intent)
            }

        }

        binding.loginKakaoBtn.setOnClickListener {
            if (LoginClient.instance.isKakaoTalkLoginAvailable(this)) {
                LoginClient.instance.loginWithKakaoTalk(this, callback = callback)
            } else {
                LoginClient.instance.loginWithKakaoAccount(this, callback = callback)
            }

            binding.loginKakaoBtn.setOnClickListener {
                val intent = Intent(this, SignUpAgreeActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun login() {
        if(binding.loginIdEt.text.toString().isEmpty()) {
            Toast.makeText(this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        if(binding.loginPwEt.text.toString().isEmpty()) {
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        // 아이디와 비밀번호 가져오기
        val id: String = binding.loginIdEt.text.toString()
        val pw: String = binding.loginPwEt.text.toString()

        // 백엔드에서 사용자 정보 확인
        checkUserExistence(id, pw)
    }

    private fun checkUserExistence(email: String, password: String) : Boolean {
        // Retrofit 서비스 인터페이스를 통해 백엔드에 사용자 정보 확인 요청
        val call = RetrofitObject.getRetrofitService.login(RetrofitClient2.RequestLogin(email, password))

        call.enqueue(object : Callback<RetrofitClient2.ResponseLogin> {
            override fun onResponse(
                call: Call<RetrofitClient2.ResponseLogin>,
                response: Response<RetrofitClient2.ResponseLogin>
            ) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    Log.d("SignUpResponse", "response값: $responseData")
                    if (responseData != null) {
                        // 사용자 정보가 존재하면 MainActivity 시작
                        startMainActivity()
                    } else {
                        // 사용자 정보가 존재하지 않으면 토스트 메시지 표시
                        Toast.makeText(this@LoginActivity, "회원 정보가 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // 처리되지 않은 응답에 대한 처리
                    Log.e("LoginActivity", "응답 실패: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.ResponseLogin>, t: Throwable) {
                // 실패에 대한 처리
                Log.e("LoginActivity", "에러: ${t.message}")
            }
        })
        return false
    }

    private fun saveJwt2(jwt: String) {
        val spf = getSharedPreferences("auth2", MODE_PRIVATE)
        val editor = spf.edit()

        editor.putString("jwt", jwt)
        editor.apply()
    }

    private fun startMainActivity() {
        // MainActivity 시작
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // 뒤로 가기 버튼으로 LoginActivity로 돌아가지 않도록 LoginActivity 종료
    }

    override fun onLoginSuccess(code: Int, result: Result) {
        when(code) {
            1000->{
                saveJwt2(result.jwt)
                startMainActivity()
            }
        }
    }

    override fun onLoginFailure() {
        //실패 처리
    }

    private fun goFindid() {
        binding.loginFindIdBtn.setOnClickListener {
            val intent = Intent(this, SignUpFindidActivity::class.java)
            startActivity(intent)
        }
    }

    private fun goResetpw() {
        binding.loginResetPwBtn.setOnClickListener {
            val intent = Intent(this, SignUpFindpwActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkLoginInfo() {
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                Toast.makeText(this, "토큰 정보 보기 실패", Toast.LENGTH_SHORT).show()
            } else if (tokenInfo != null) {
                Toast.makeText(this, "토큰 정보 보기 성공", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//                finish()
            }
        }
    }

//    private fun saveTokenInfo(context: Context, accessToken: String?, refreshtoken:String?) {
//        val sharedPref = context.getSharedPreferences("TOBEMOM", Context.MODE_PRIVATE)
//        with(sharedPref.edit()) {
//            accessToken?.let { putString("accessToken", it) }
//            Log.d("loginToken",accessToken.toString())
//            refreshtoken?.let { putString("refreshtoken", it) }
//            apply()
//        }
//    }
}