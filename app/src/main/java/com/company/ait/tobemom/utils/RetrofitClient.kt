package com.company.ait.tobemom.utils

import com.company.ait.tobemom.api.ChatInterface
import com.company.ait.tobemom.api.UserInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val BASE_URL = "http://sidemoney.site"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userApi = retrofit.create<UserInterface>(UserInterface::class.java)
    val chatApi = retrofit.create<ChatInterface>(ChatInterface::class.java)

}