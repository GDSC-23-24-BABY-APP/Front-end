package com.company.ait.tobemom.utils

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitAPI {
    @GET("/login/oauth2/code/kakao")
    fun kakaoLogin(): Call<RetrofitClient2.ResponseKakaoLogin>

    @POST("/api/user/login")
    fun login(@Body request: RetrofitClient2.RequestLogin): Call<RetrofitClient2.ResponseLogin>

    @POST("/api/user/join")
    fun signup(@Body request: RetrofitClient2.SignupRequest): Call<Void>

}