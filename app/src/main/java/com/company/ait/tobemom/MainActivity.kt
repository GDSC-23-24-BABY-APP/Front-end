package com.company.ait.tobemom

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivityMainBinding
import com.kakao.sdk.common.util.Utility

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigation()

        val keyHash = Utility.getKeyHash(this)
        Log.d("Hash", keyHash)

//        // FCM SDK 초기화 - 안드로이드 스튜디오에서 FCM token 얻기
//        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
//            if (!task.isSuccessful) {
//                Log.e(TAG, "Fetching FCM registration token failed", task.exception)
//                return@OnCompleteListener
//            }
//
//            var token = task.result ?: return@OnCompleteListener
//
//            Log.e(TAG, "token: $token")
//            Toast.makeText(this, token, Toast.LENGTH_SHORT).show()
//        })
//
//        try {
//            val title = "To Be Mom"
//            val body = "Complete today's miscarriage/stillbirth checklist and review the diagnostic results !"
//
//            val fcmtoken = getFcmToken(this)
//            val call = RetrofitObject.getRetrofitService.fcm("Bearer $fcmtoken", title, body)
//            Log.d("fcm","token ${fcmtoken}")
//
//            call.enqueue(object : Callback<RetrofitClient2.FcmResponse> {
//                override fun onResponse(
//                    call: Call<RetrofitClient2.FcmResponse>,
//                    response: Response<RetrofitClient2.FcmResponse>
//                ) {
//                    Log.d("fcm", "${response.toString()}")
//                    if (response.isSuccessful) {
//                        val fcmData = response.body()
//                        // 성공적인 응답 처리
//                        Log.d("fcm", "${fcmData.toString()}")
//                    } else {
//                        // 서버 오류 처리
//                        Log.e("서버 오류", response.errorBody()?.string() ?: "알 수 없는 오류")
//                    }
//                }
//
//                override fun onFailure(
//                    call: Call<RetrofitClient2.FcmResponse>,
//                    t: Throwable
//                ) {
//                    // 네트워크 실패 처리
//                    Log.e("CheckHealth", "API 호출 실패: ${t.message}")
//                }
//            })
//
//        } catch (e: NumberFormatException) {
//            // 무게가 유효한 float이 아닌 경우 처리
//            Log.e("입력 오류", "유효하지 않은 무게 형식")
//        }

    }
    private fun initBottomNavigation(){

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.mainBnv.setOnItemSelectedListener{ item ->
            when (item.itemId) {

                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.chatFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, ChatFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.checkFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, ChecklistFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.newsFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, NewsFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.myPageFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, MypageFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
//
//    fun getFcmToken(context: Context): String? {
//        val task = FirebaseMessaging.getInstance().token
//        return try {
//            Tasks.await(task)
//        } catch (e: ExecutionException) {
//            // 토큰을 얻는 중에 예외가 발생했을 경우
//            null
//        } catch (e: InterruptedException) {
//            // 작업이 중단되었을 경우
//            null
//        }
//    }
}