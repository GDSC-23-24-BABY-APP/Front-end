package com.company.ait.tobemom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), LoginView {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginToSigninBtn.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.loginLoginBtn.setOnClickListener {
            login()
        }
    }

    private fun login() {

        //아이디 입력 안했을 때 오류 처리
        if (binding.loginIdEt.text.toString().isEmpty()) {
            Toast.makeText(this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        //비밀번호 입력 안했을 때 오류 처리
        if (binding.loginPwEt.text.toString().isEmpty()) {
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        val id: String = binding.loginIdEt.text.toString()
        val pw: String = binding.loginPwEt.text.toString()

//        val songDB = SongDatabase.getInstance(this)!!
//        val user = songDB.UserDao().getUser(email,pwd)
//
//        user?.let {
//            Log.d("LOGIN_ACT/GET_USER", "userId : ${user.id}, $user")
//            //saveJwt(user.id)
//            startMainActivity()
//        }

        val authService = AuthService()
        authService.setLoginView(this)

        authService.login(User(id, pw, ""))

        Toast.makeText(this, "회원 정보가 존재하지 않습니다.", Toast.LENGTH_SHORT).show()

    }

//    private fun saveJwt(jwt: Int) {
//        val spf = getSharedPreferences("auth", MODE_PRIVATE)
//        val editor = spf.edit()
//
//        editor.putInt("jwt", jwt)
//        editor.apply()
//    }

    private fun saveJwt2(jwt: String) {
        val spf = getSharedPreferences("auth2", MODE_PRIVATE)
        val editor = spf.edit()

        editor.putString("jwt", jwt)
        editor.apply()
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onLoginSuccess(code: Int, result: Result) {
        when (code) {
            1000 -> {
                saveJwt2(result.jwt)
                startMainActivity()
            }
        }
    }

    override fun onLoginFailure() {
        //실패 처리
    }
}