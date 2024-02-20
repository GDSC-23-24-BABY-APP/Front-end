package com.company.ait.tobemom.utils

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface RetrofitAPI {

    @GET("/login/oauth2/code/google")
    fun googleLogin(
        @Query("code") code: String,
        @Path("registrationId") registrationId: String
    ): Call<RetrofitClient2.ResponseGoogleLogin>

    @POST("/api/user/login")
    fun login(@Body request: RetrofitClient2.RequestLogin): Call<RetrofitClient2.ResponseLogin>

    @POST("/api/user/join")
    fun signup(@Body request: RetrofitClient2.SignupRequest): Call<Void>

    @GET("/api/checklist/num")
    fun getChecklist(@Query("num") num: Int): Call<RetrofitClient2.ChecklistResponse>

    @POST("/api/checklist/cal-result")
    fun sendChecklist(@Body request: RetrofitClient2.ChecklistResultData): Call<RetrofitClient2.ChecklistCalResultResponse>

    @POST("/health/new")
    fun checkHealth(@Header("Authorization") token:String, @Body request: RetrofitClient2.CheckHealth): Call<RetrofitClient2.ResponseCheckHealth>

    @POST("/api/user/family/create")
    fun babyAdd(@Body request: RetrofitClient2.BabyInfoRequest): Call<String>

}