package com.company.ait.tobemom.dto

import com.google.gson.annotations.SerializedName

data class ChatRes (
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String?,
    @SerializedName("result") val result: ChatResult?,
)
data class ChatResult(
    @SerializedName("chatRoomId") val chatRoomId: Int?,
    @SerializedName("firstMessage") val firstMessage: String?,
)

data class ChatReq (
    @SerializedName("communicator") val communicator: String?,
    @SerializedName("specific_situation") val specific_situation: String?,
    @SerializedName("conversation_subject") val conversation_subject: String?,
    @SerializedName("mbti") val mbti: String?,
    @SerializedName("characteristic") val characteristic: String?,
)

data class SendChatRes (
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String?,
    @SerializedName("result") val result: SendChatResult?,
)
data class SendChatResult(
    @SerializedName("chatRoomId") val chatRoomId: Int?,
    @SerializedName("firstMessage") val firstMessage: String?,
)

data class SendChatReq(
    @SerializedName("chatRoomId") val chatRoomId: Int?,
    @SerializedName("message") val message: String?,
)