package com.company.ait.tobemom

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.ait.tobemom.databinding.ActivityChatBinding
import com.company.ait.tobemom.dto.MessageAdapter
import com.company.ait.tobemom.dto.MessageModel
import com.company.ait.tobemom.dto.SendChatReq
import com.company.ait.tobemom.dto.SendChatRes
import com.company.ait.tobemom.utils.GlobalApplication
import com.company.ait.tobemom.utils.RetrofitObject
import retrofit2.Call
import retrofit2.Callback


class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //messageList = ArrayList()
        val messageAdapter: MessageAdapter = MessageAdapter()
        binding.rvChat.layoutManager = LinearLayoutManager(this)
        binding.rvChat.adapter = messageAdapter

        val firstm = intent.getStringExtra("firstm").toString()
        messageAdapter.apply {
            addItem(MessageModel.ReceiverMessage(firstm))
        }


        //전송
        binding.btnChatSend.setOnClickListener {
            val message = binding.etChatInput.text.toString()
            val roomId = intent.getIntExtra("roomId", 1)

            send(SendChatReq(roomId, message), messageAdapter)
        }

        //가져오기
    }


    private fun send(sendReq: SendChatReq, messageAdapter: MessageAdapter) {
        messageAdapter.apply {
            addItem(MessageModel.SenderMessage(binding.etChatInput.text.toString()))
        }

        RetrofitObject.chatApi.sendChat(GlobalApplication.spf.Jwt, sendReq).enqueue(object : Callback<SendChatRes> {
            override fun onResponse(call: Call<SendChatRes>, response: retrofit2.Response<SendChatRes>) {
                if(response.isSuccessful){
                    val sendChatResult: SendChatRes? = response.body()
                    Log.d("SENDCHAT", "onResponse 성공: " + sendChatResult?.toString())



                }else{
                    Log.d("SENDCHAT", "onResponse 실패")
                }
            }
            override fun onFailure(call: Call<SendChatRes>, t: Throwable) {
                Log.d("SENDCHAT/ERROR", t.message.toString())
            }


        })
    }
}