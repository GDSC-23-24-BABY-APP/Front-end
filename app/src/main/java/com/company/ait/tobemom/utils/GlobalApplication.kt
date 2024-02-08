package com.company.ait.tobemom.utils

import android.content.Context

class MySharedPreference (context: Context) {

    private val spf = context.getSharedPreferences("user", Context.MODE_PRIVATE)

    var accessToken: String?
        get() = spf.getString("accesstoekn", "")
        set(value) = spf.edit().putString("accesstoekn", value).apply()
    var Jwt: String?
        get() = spf.getString("jwt", "")
        set(value) = spf.edit().putString("jwt", value).apply()
}