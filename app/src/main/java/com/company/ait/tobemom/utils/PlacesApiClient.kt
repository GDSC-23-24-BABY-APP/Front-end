package com.company.ait.tobemom.utils

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//object PlacesApiClient {
//    private const val BASE_URL = "https://maps.googleapis.com/maps/api/place/"
//
//    private val retrofit = Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    private val apiService: PlacesApiService = retrofit.create(PlacesApiService::class.java)
//
//    fun getNearbyHospitals(
//        location: String,
//        radius: Int,
//        type: String,
//        apiKey: String,
//        callback: (List<RetrofitClient2.Hospital>?) -> Unit
//    ) {
//        val call = apiService.getNearbyHospitals(location, radius, apiKey)
//        call.enqueue(object : retrofit2.Callback<RetrofitClient2.PlacesApiResponse> {
//            override fun onResponse(
//                call: Call<RetrofitClient2.PlacesApiResponse>,
//                response: retrofit2.Response<RetrofitClient2.PlacesApiResponse>
//            ) {
//                if (response.isSuccessful) {
//                    val placesResponse = response.body()
//                    val hospitals = placesResponse?.results
//                    callback(hospitals)
//                } else {
//                    callback(null)
//                }
//            }
//
//            override fun onFailure(call: Call<RetrofitClient2.PlacesApiResponse>, t: Throwable) {
//                callback(null)
//            }
//        })
//    }
//}