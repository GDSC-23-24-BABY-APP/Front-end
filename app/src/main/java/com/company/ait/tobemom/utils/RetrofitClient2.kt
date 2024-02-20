package com.company.ait.tobemom.utils

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.google.maps.android.data.Geometry
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

    data class CheckHealth(
        @SerializedName("weight")
        val weight: Float,
        @SerializedName("healthInfoList")
        val healthInfoList: IntArray,
        @SerializedName("healthDiray")
        val healthDiary: String,
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


    data class PlacesApiResponse(
        @SerializedName("results") val hospitals: List<Hospital>
    )

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
}