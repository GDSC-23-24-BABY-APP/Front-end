package com.company.ait.tobemom

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.ait.tobemom.databinding.FragmentChatBinding
import com.company.ait.tobemom.dto.MessageAdapter
import com.company.ait.tobemom.dto.MessageModel
import com.company.ait.tobemom.dto.SendChatReq
import com.company.ait.tobemom.dto.SendChatRes
import com.company.ait.tobemom.dto.UserMessage
import com.company.ait.tobemom.utils.GlobalApplication
import com.company.ait.tobemom.utils.RetrofitObject
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private lateinit var generativeModel: GenerativeModel
    private lateinit var messageAdapter: MessageAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragment의 레이아웃을 inflate하고 바인딩 객체를 초기화
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //GenerativeModel 초기화
        generativeModel = GenerativeModel(
            modelName = "gemini-pro",
            apiKey = "AIzaSyCIq2Ajd5ZFqK25Dy6zFpcqRboMEluizJs"
        )

        messageAdapter = MessageAdapter()
        binding.rvChat.layoutManager = LinearLayoutManager(requireContext())
        binding.rvChat.adapter = messageAdapter

        val firstMessage = arguments?.getString("firstm") ?: ""
        messageAdapter.apply {
            addItem(MessageModel.ReceiverMessage(firstMessage))
        }

        binding.btnChatSend.setOnClickListener {
            val userMessage = binding.etChatInput.text.toString()
            val roomId = arguments?.getInt("roomId") ?: 1

            // Coroutine을 사용하여 비동기로 호출
            lifecycleScope.launch {
                processWithGemini(userMessage, roomId)
            }
        }
    }

    private suspend fun processWithGemini(userMessage: String, roomId: Int) {
        val prompt = "사용자 메시지에 응답: '$userMessage'"

        try {
            // Coroutine을 사용하여 비동기로 호출
            val generatedResponse = withContext(Dispatchers.IO) {
                generativeModel.generateContent(prompt)
            }

            // 생성된 응답에서 텍스트 가져오기
            val generatedReply = generatedResponse.text

            if (generatedReply != null) {
                // 생성된 응답을 RecyclerView 어댑터에 추가
                withContext(Dispatchers.Main) {
                    messageAdapter.addItem(MessageModel.ReceiverMessage(generatedReply))
                }
            } else {
                Log.d("GEMINI_GENERATE", "응답이 없거나 text가 null입니다.")
            }

            // 사용자의 메시지를 보내고 서버 응답을 처리
            val sendReq = SendChatReq(roomId, userMessage)
            send(sendReq)
        } catch (e: Exception) {
            Log.d("GEMINI_GENERATE", "API 호출 실패: ${e.message}")
        }
    }

    private fun send(sendReq: SendChatReq) {
        messageAdapter.apply {
            // null 체크를 위해 안전한 호출 연산자 ?. 사용
            val message = sendReq.message ?: "기본 메시지"

            addItem(MessageModel.SenderMessage(message))
        }

        try {
            val jwt = GlobalApplication.spf.Jwt
            if (jwt != null) {
                RetrofitObject.chatApi.sendChat(jwt, sendReq)
                    .enqueue(object : Callback<SendChatRes> {
                        override fun onResponse(call: Call<SendChatRes>, response: Response<SendChatRes>) {
                            if (response.isSuccessful) {
                                val sendChatResult: SendChatRes? = response.body()
                                Log.d("SENDCHAT", "onResponse 성공: " + sendChatResult?.toString())
                            } else {
                                Log.d("SENDCHAT", "onResponse 실패")
                            }
                        }

                        override fun onFailure(call: Call<SendChatRes>, t: Throwable) {
                            Log.d("SENDCHAT/ERROR", t.message.toString())
                        }
                    })
            } else {
                Log.d("SENDCHAT/ERROR", "Jwt is null")
            }
        } catch (e: Exception) {
            Log.d("SENDCHAT/ERROR", e.message.toString())
        }
    }

}