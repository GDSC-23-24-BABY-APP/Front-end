package com.company.ait.tobemom.dto

import com.google.gson.annotations.SerializedName

data class UserRes(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String?,
    @SerializedName("result") val result: UserMessage?,
)
data class UserMessage(
    @SerializedName("resultMessage") val resultMessage: String?,
    @SerializedName("userId") val userId: Int?,
    @SerializedName("nickname") val nickname: String?,
    @SerializedName("jwtAccessToken") val accessJwt: String?,
    @SerializedName("jwtRefreshToken") val refreshJwt: String?,
)

data class LoginReq(
    @SerializedName(value = "accessToken") val accessToken: String?,
)

data class SignupReq(
    @SerializedName(value = "accessToken")val accessToken: String?,
    @SerializedName("nickname") val nickname: String?,
)