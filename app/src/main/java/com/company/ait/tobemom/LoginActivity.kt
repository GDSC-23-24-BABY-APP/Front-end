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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), LoginView {

    lateinit var binding: ActivityLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient

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

        //구글 로그인
        setupGoogleSignInClient()
        addListener()

        //아이디 비번 찾기
        goFindid()
        goResetpw()
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
                        val jwtToken = responseData.data.token // 실제 받아온 토큰 값으로 대체
                        saveJwt2(jwtToken)
                        Log.d("SignUpResponseToken", "token값: $jwtToken")
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

    //Google Login
    private fun setupGoogleSignInClient() {
        val googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestScopes(Scope("https://www.googleapis.com/auth/userinfo.profile"))
            .requestScopes(Scope("https://www.googleapis.com/auth/userinfo.email"))
            .requestServerAuthCode(getString(R.string.googleClientID))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOption)
    }

    private fun addListener() {
        binding.loginGoogleBtn.setOnClickListener {
            requestGoogleLogin()
        }
    }

    private fun requestGoogleLogin() {
        googleSignInClient.signOut()
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN_IN_REQUEST_CODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task.getResult(ApiException::class.java)
                // 사용자 이름, 이메일 등을 얻을 수 있음
                val userName = account?.displayName
                val userEmail = account?.email
                val authCode = account?.serverAuthCode // 구글에서 받은 인증 코드 가져오기
                val registrationId = account?.id // 등록 ID 가져오기 (구글 계정의 고유 ID로 대체 가능)

                Log.d("GoogleLogin", "$userName, $userEmail, $authCode, $registrationId")

//                // 사용자 정보를 담은 Bundle 생성
//                val bundle = Bundle().apply {
//                    putString("userName", userName)
//                    putString("userEmail", userEmail)
//                }
//
//                // HomeFragment 생성 및 Bundle 전달
//                val homeFragment = HomeFragment()
//                homeFragment.arguments = bundle
//
//                // FragmentTransaction을 사용하여 HomeFragment를 화면에 추가
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.fragment_container, homeFragment)
//                    .commit()


                // Google 로그인 결과를 처리한 후 SignUpAgreeActivity로 이동
                val intent = Intent(this, HomeFragment::class.java)
                // 사용자 정보를 SignUpAgreeActivity에 전달
                intent.putExtra("userName", userName)
                intent.putExtra("userEmail", userEmail)
                startActivity(intent)

                // 백엔드 서버로 사용자 정보와 구글에서 받은 인증 코드 및 등록 ID 전송
                sendUserInfoToServer(userName, userEmail, authCode, registrationId)
            } catch (e: ApiException) {
                Log.e(LoginActivity::class.java.simpleName, e.stackTraceToString())
            }
        }
    }

    private fun sendUserInfoToServer(userName: String?, userEmail: String?, authCode: String?, registrationId: String?) {
        // 사용자 정보를 서버로 전송하는 API 호출
        // Retrofit 등을 사용하여 서버에 POST 요청 등을 보낼 수 있습니다.
        val retrofitService = RetrofitObject.getRetrofitService
        if (authCode != null && registrationId != null) {
            val call = retrofitService.googleLogin(authCode, registrationId)

            call.enqueue(object : Callback<RetrofitClient2.ResponseGoogleLogin> {
                override fun onResponse(call: Call<RetrofitClient2.ResponseGoogleLogin>, response: Response<RetrofitClient2.ResponseGoogleLogin>) {
                    Log.d("GoogleLogin", "${response.toString()}}")
                    if (response.isSuccessful) {
                        // 응답 성공 처리
                        val responseData = response.body()
                        Log.d("GoogleLogin", "${responseData.toString()}")
                        if (responseData != null) {
                            if (responseData.status == "success") {
                                val token = responseData.data.token
                                saveTokenInfo(this@LoginActivity,token)
                                val intent = Intent(this@LoginActivity, SignUpAgreeActivity::class.java)
                                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                                finish()
                            } else {
                                Toast.makeText(this@LoginActivity, responseData.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<RetrofitClient2.ResponseGoogleLogin>, t: Throwable) {
                    // 네트워크 요청 실패 처리
                    val errorMessage = "Call Failed: ${t.message}"
                    Log.e("GoogleLogin", errorMessage)
                }
            })
        } else {
            // authCode나 registrationId가 null인 경우 처리
            Log.e("GoogleLogin", "Failed to retrieve authCode or registrationId")
        }
    }

    companion object {
        private const val GOOGLE_SIGN_IN_REQUEST_CODE = 1001
    }

    private fun saveTokenInfo(context: Context, token: String?) {
        val sharedPref = context.getSharedPreferences("TOBEMOM", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            token?.let { putString("token", it) }
            Log.d("loginToken",token.toString())
            apply()
        }
    }
}