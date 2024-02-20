package com.company.ait.tobemom.utils

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {

//    companion object {
//        lateinit var spf : MySharedPreference
//    }

    override fun onCreate() {
//        spf = MySharedPreference(applicationContext)
        super.onCreate()

        // Kakao SDK 초기화
        KakaoSdk.init(this, "612da977c6ea46f65349319262a190e9")
    }

//    class MySharedPreference (context: Context) {
//        private val prefNm = "TOBEMOM"
//        private val spf = context.getSharedPreferences(prefNm, Context.MODE_PRIVATE)
//
//        var token: String?
//            get() = spf.getString("token", null)
//            set(value) {
//                spf.edit().putString("token", value).apply()
//            }
//        var accessToken: String?
//            get() = spf.getString("accessToken", "") // Fix typo here
//            set(value) = spf.edit().putString("accessToken", value).apply() // Fix typo here
//        var Jwt: String?
//            get() = spf.getString("jwt", "")
//            set(value) = spf.edit().putString("jwt", value).apply()
//    }
}