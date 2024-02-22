package com.company.ait.tobemom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.company.ait.tobemom.databinding.FragmentChatBinding


class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        val view = binding.root

//        //messageList = ArrayList()
//        val messageAdapter: MessageAdapter = MessageAdapter()
//        binding.rvChat.layoutManager = LinearLayoutManager(this)
//        binding.rvChat.adapter = messageAdapter
//
//        val firstm = intent.getStringExtra("firstm").toString()
//        messageAdapter.apply {
//            addItem(MessageModel.ReceiverMessage(firstm))
//        }
//
//
//        //전송
//        binding.btnChatSend.setOnClickListener {
//            val message = binding.etChatInput.text.toString()
//            val roomId = intent.getIntExtra("roomId", 1)
//
//            send(SendChatReq(roomId, message), messageAdapter)
//        }

        //가져오기


        return view
    }

//    private fun send(sendReq: SendChatReq, messageAdapter: MessageAdapter) {
//        messageAdapter.apply {
//            addItem(MessageModel.SenderMessage(binding.etChatInput.text.toString()))
//        }
//
//        RetrofitObject.chatApi.sendChat(GlobalApplication.spf.Jwt, sendReq).enqueue(object : Callback<SendChatRes> {
//            override fun onResponse(call: Call<SendChatRes>, response: retrofit2.Response<SendChatRes>) {
//                if(response.isSuccessful){
//                    val sendChatResult: SendChatRes? = response.body()
//                    Log.d("SENDCHAT", "onResponse 성공: " + sendChatResult?.toString())
//
//
//
//                }else{
//                    Log.d("SENDCHAT", "onResponse 실패")
//                }
//            }
//            override fun onFailure(call: Call<SendChatRes>, t: Throwable) {
//                Log.d("SENDCHAT/ERROR", t.message.toString())
//            }
//
//
//        })
//    }
}