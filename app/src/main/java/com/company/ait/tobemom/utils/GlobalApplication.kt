package com.company.ait.tobemom.utils

import android.app.Application
import android.content.Context
import com.company.ait.tobemom.api.ChatInterface
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {

    companion object {
        lateinit var spf: MySharedPreference

        lateinit var chatApi: ChatInterface
    }

    override fun onCreate() {
        spf = MySharedPreference(applicationContext)
        super.onCreate()

        // Kakao SDK 초기화
        KakaoSdk.init(this, "612da977c6ea46f65349319262a190e9")

    }

    class MySharedPreference(context: Context) {
        private val prefNm = "TOBEMOM"
        private val spf = context.getSharedPreferences(prefNm, Context.MODE_PRIVATE)

        var Jwt: String?
            get() = spf.getString("jwt", null)
            set(value) = spf.edit().putString("jwt", value).apply()
    }
}
