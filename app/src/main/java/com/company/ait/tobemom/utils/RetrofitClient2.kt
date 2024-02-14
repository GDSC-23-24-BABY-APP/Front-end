package com.company.ait.tobemom.utils

import com.google.gson.annotations.SerializedName
import java.util.Date

class RetrofitClient2 {

    data class ResponseKakaoLogin(
        @SerializedName("status")
        val status: String,
        @SerializedName("data")
        val data: KakaoData,
        @SerializedName("message")
        val message: String
    )

    data class KakaoData(
        @SerializedName("userId")
        val userId: Int,
        @SerializedName("token")  //jwt 토큰
        val token: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("username")
        val username: String
    )
    data class SignupRequest(
        @SerializedName("email")
        val email: String,
        @SerializedName("password")
        val password: String,
//        @SerializedName("birthDate")
//        val birthDate: Date,
//        @SerializedName("username")
//        val username: String,
        @SerializedName("nickname")
        val nickname: String,
//        @SerializedName("role")
//        val role: String,
//        @SerializedName("isSocialLogin")
//        val isSocialLogin: Int,
//        @SerializedName("gender")
//        val gender: String,
//        @SerializedName("ageRange")
//        val ageRange: String,
//        @SerializedName("birthYear")
//        val birthYear: String,
        @SerializedName("phoneNumber")
        val phoneNumber: String,
        @SerializedName("familyType")
        val familyType: String,
        @SerializedName("babyName")
        val babyName: String,
        @SerializedName("babyBirthDate")
        val babyBirthDate: Date
    )

//    data class SignupResponse (
//        @SerializedName("")
//        val userid: Long
//    )

    data class RequestLogin(
        @SerializedName("email")
        val email: String,
        @SerializedName("password")
        val password: String
    )

    data class ResponseLogin(
        @SerializedName("status")
        val status: String,
        @SerializedName("data")
        val data: LoginData,
        @SerializedName("message")
        val message: String
    )

    data class LoginData(
        @SerializedName("userId")
        val userId: Int,
        @SerializedName("token")
        val token: String,
        @SerializedName("email")
        val id: String,
        @SerializedName("username")
        val username: String
    )
}