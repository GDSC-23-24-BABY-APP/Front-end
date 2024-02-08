package com.company.ait.tobemom.api

import com.company.ait.tobemom.dto.ChatReq
import com.company.ait.tobemom.dto.ChatRes
import com.company.ait.tobemom.dto.SendChatReq
import com.company.ait.tobemom.dto.SendChatRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ChatInterface {
    @POST("/api/chat/room")
    fun createChat(
        @Header("jwtAccessToken") jwtAccessToken: String?,
        @Body chatReq: ChatReq) : Call<ChatRes>

    @POST("/api/chat")
    fun sendChat(
        @Header("jwtAccessToken") jwtAccessToken: String?,
        @Body sendChatReq: SendChatReq) : Call<SendChatRes>
}