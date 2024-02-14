package com.company.ait.tobemom.utils

import com.company.ait.tobemom.api.ChatInterface
import com.company.ait.tobemom.api.UserInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {

    private const val URL = "http://34.64.44.183:8080"

    private val logging = HttpLoggingInterceptor().apply {
        // 요청과 응답의 본문 내용까지 로그에 포함
        level = HttpLoggingInterceptor.Level.BODY
    }

    // OKHttp 클라이언트를 구성
    // 이 클라이언트는 로깅 인터셉터를 추가하여 네트워크 요청 시 로그가 생성되도록 함
    // cURL을 확인 하기 위해 사용
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val getRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val getRetrofitService: RetrofitAPI by lazy {  getRetrofit.create(RetrofitAPI::class.java) }
    val userApi = getRetrofit.create<UserInterface>(UserInterface::class.java)
    val chatApi = getRetrofit.create<ChatInterface>(ChatInterface::class.java)

}