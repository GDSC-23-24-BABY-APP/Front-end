package com.company.ait.tobemom.api

import com.company.ait.tobemom.dto.LoginReq
import com.company.ait.tobemom.dto.SignupReq
import com.company.ait.tobemom.dto.UserRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface UserInterface {
    @POST("/api/user/login")
    fun login(@Body loginReq: LoginReq) : Call<UserRes>

    @POST("/api/user/signup")
    fun signup(@Body signupReq: SignupReq) : Call<UserRes>
}