package com.company.ait.tobemom.utils

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesApiService {
//    @GET("nearbysearch/json")
//    fun getNearbyHospitals(
//        @Query("location") location: String,
//        @Query("radius") radius: Int,
//        @Query("type") type: String,
//        @Query("key") apiKey: String
//    ): Call<RetrofitClient2.PlacesApiResponse>

    // 병원 정보 요청하는 GET 요청을 정의
    @GET("place/nearbysearch/json?type=hospital")
    fun getNearbyHospitals(
        @Query("location") location: String,
        @Query("radius") radius: Int,
        @Query("key") apiKey: String
    ): Call<RetrofitClient2.PlacesApiResponse>
}