package com.company.ait.tobemom.utils

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitAPI {
    @GET("/login/oauth2/code/kakao")
    fun kakaoLogin(@Body request: RetrofitClient2.RequestLogin): Call<RetrofitClient2.ResponseLogin>
//    @POST("/auth/signup")
//    fun signup(@Body request: RetrofitClient2.RequestSignup): Call<RetrofitClient2.ResponseSignup>
}