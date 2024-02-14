package com.company.ait.tobemom

import android.content.Context
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

class LoginActivity : AppCompatActivity() {

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

        }

        //아이디 비번 찾기
        goFindid()
        goResetpw()

        // 로그인 정보 확인
        checkLoginInfo()

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
                        loginServer(email!!, name!!)
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

    private fun loginServer(email: String,name: String): Boolean {
        val call = RetrofitObject.getRetrofitService.kakaoLogin(RetrofitClient2.RequestLogin(email, name))
        call.enqueue(object : Callback<RetrofitClient2.ResponseLogin> {
            override fun onResponse(
                call: Call<RetrofitClient2.ResponseLogin>,
                response: Response<RetrofitClient2.ResponseLogin>
            ) {
                Log.d("login", response.toString())
                if (response.isSuccessful) {
                    val response = response.body()
                    Log.d("login", response.toString())
                    if (response != null) {
                        if (response.isSuccess) {
                            val accessToken = response.result.accessToken
                            val refreshToken = response.result.refreshToken
                            saveTokenInfo(this@LoginActivity,accessToken,refreshToken)
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                            finish()
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                response.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                }
            }

            override fun onFailure(
                call:
                Call<RetrofitClient2.ResponseLogin>, t: Throwable
            ) {
                val errorMessage = "Call Failed: ${t.message}"
                Log.d("login", errorMessage)
            }
        })
        return false
    }

    private fun saveTokenInfo(context: Context, accessToken: String?, refreshtoken:String?)
    {
        val sharedPref = context.getSharedPreferences("TOBEMOM", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            accessToken?.let { putString("accessToken", it) }
            Log.d("loginToken",accessToken.toString())
            refreshtoken?.let { putString("refreshtoken", it) }
            apply()
        }
    }
}