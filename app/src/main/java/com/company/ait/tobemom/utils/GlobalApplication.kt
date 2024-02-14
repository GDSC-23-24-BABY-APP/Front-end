package com.company.ait.tobemom.utils

import android.app.Application
import android.content.Context
//import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {

    companion object {
        lateinit var spf : MySharedPreference
    }

    override fun onCreate() {
        spf = MySharedPreference(applicationContext)
        super.onCreate()

        // Kakao SDK 초기화
        //KakaoSdk.init(this, "612da977c6ea46f65349319262a190e9")
    }

    class MySharedPreference (context: Context) {

        private val spf = context.getSharedPreferences("user", Context.MODE_PRIVATE)

        var accessToken: String?
            get() = spf.getString("accesstoekn", "")
            set(value) = spf.edit().putString("accesstoekn", value).apply()
        var Jwt: String?
            get() = spf.getString("jwt", "")
            set(value) = spf.edit().putString("jwt", value).apply()
    }
}
