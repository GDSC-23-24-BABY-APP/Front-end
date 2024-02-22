package com.company.ait.tobemom.utils

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.Date

class RetrofitClient2 {

    data class SignupRequest(
        @SerializedName("email")
        val id: String,
        @SerializedName("password")
        val password: String,
//        @SerializedName("birthDate")
//        val birthDate: Date,
        @SerializedName("username")
        val username: String,
//        @SerializedName("nickname")
//        val nickname: String,
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
//        @SerializedName("phoneNumber")
//        val phoneNumber: String,
//        @SerializedName("familyType")
//        val familyType: String,
        @SerializedName("babyName")
        val babyName: String,
        @SerializedName("babyBirthDate")
        val babyBirthDate: String
    )

    data class ResponseGoogleLogin(
        @SerializedName("status")  //성공여부
        val status: String,
        @SerializedName("data")
        val data: GoogleData,
        @SerializedName("message")
        val message: String
    )

    data class GoogleData(
        @SerializedName("userId")
        val userId: Int,
        @SerializedName("token")  //jwt 토큰
        val token: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("username")
        val username: String
    )

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

    data class BabyInfoRequest(
        @SerializedName("babyName")
        val babyName: String,
        @SerializedName("babyBirthDate")
        val babyBirthDate: Date
    )

    data class CheckHealth(
        @SerializedName("weight")
        val weight: Float,
        @SerializedName("healthInfoList")
        val healthInfoList: IntArray,
        @SerializedName("healthDiary")
        val healthDiary: String
//        @SerializedName("healthState")
//        val healthState: Int
    )
    data class ResponseCheckHealth(
        @SerializedName("status")
        val status: String,
        @SerializedName("data")
        val data: Long,
        @SerializedName("message")
        val message: String
    )

    data class HealthListResponse(
        val status: String,
        val data: List<HealthData>,
        val message: String
    )

    data class HealthData(
        val id: Int,
        val weight: Int,
        val healthInfoList: List<Int>,
        val healthDiary: String,
        val healthState: Int,
        val createdDate: String
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.createIntArray()?.toList() ?: emptyList(),
            parcel.readString() ?: "",
            parcel.readInt(),
            parcel.readString() ?: ""
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeInt(weight)
            parcel.writeIntArray(healthInfoList.toIntArray())
            parcel.writeString(healthDiary)
            parcel.writeInt(healthState)
            parcel.writeString(createdDate)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<HealthData> {
            override fun createFromParcel(parcel: Parcel): HealthData {
                return HealthData(parcel)
            }

            override fun newArray(size: Int): Array<HealthData?> {
                return arrayOfNulls(size)
            }
        }
    }


    //Checklist Form Data
    data class ChecklistResponse(
        @SerializedName("status") val status: String,
        @SerializedName("data") val data: ChecklistData,
        @SerializedName("message") val message: String
    )

    data class ChecklistData(
        @SerializedName("id") val id: Int,
        @SerializedName("title") val title: String,
        @SerializedName("num") val num: Int,
        @SerializedName("questions") val questions: List<Question>
    )

    data class Question(
        @SerializedName("createdAt") val createdAt: String,
        @SerializedName("modifiedAt") val modifiedAt: String,
        @SerializedName("id") val id: Int,
        @SerializedName("num") val num: Int,
        @SerializedName("content") val content: String,
        @SerializedName("symptom") val symptom: String,
        @SerializedName("risk") val risk: Int
    )

    //Checklist Result
    data class ChecklistResultData(
        @SerializedName("checkListNum") val checkListNum: Int,
        @SerializedName("answerList") val answerList: List<Int>
    )
    data class ChecklistCalResultResponse(
        @SerializedName("status") val status: String,
        @SerializedName("data") val data: ResultData,
        @SerializedName("message") val message: String
    )
    data class ResultData(
        @SerializedName("result") val result: Int,
        @SerializedName("checkedQ") val checkedQ: List<String>
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            mutableListOf<String>().apply {
                parcel.readStringList(this)
            }
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(result)
            parcel.writeStringList(checkedQ)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<ResultData> {
            override fun createFromParcel(parcel: Parcel): ResultData {
                return ResultData(parcel)
            }

            override fun newArray(size: Int): Array<ResultData?> {
                return arrayOfNulls(size)
            }
        }
    }

    // 병원 정보를 담는 데이터 클래스
    data class Hospital(
        @SerializedName("name") val name: String,
        @SerializedName("address") val address: String,
        @SerializedName("rating") val rating: Double
    )
    data class Geometry(
        val location: Location
    )
    data class Location(
        val lat: Double,
        val lng: Double
    )

    data class sttResponse(
        @SerializedName("audioFile")
        val audioFile: ByteArray
    )

    data class FcmResponse(
        @SerializedName("status")
        val status: String,
        @SerializedName("data")
        val data: String,
        @SerializedName("message")
        val message: String
    )
}