package com.company.ait.tobemom.dto

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.company.ait.tobemom.databinding.ItemChatmessageBinding
import com.company.ait.tobemom.databinding.ItemUsermessageBinding
import java.util.*
import kotlin.collections.ArrayList

//data class Message(val text:String, val sendId: String?)
//
//class MessageAdapter(val context: Context, val chatList :ArrayList<Message>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
//
//    private val receive = 1
//    private val send= 2
//
//    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return if(viewType == 1){
//            val view: View = LayoutInflater.from(context).inflate(R.layout.item_message, viewGroup, false)
//            ReceiveViewHolder(view)
//        }else{
//            val view: View = LayoutInflater.from(context).inflate(R.layout.item_usermessage, viewGroup, false)
//            SendViewHolder(view)
//        }
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val currentMessage = chatList[position]
//
//        //보내는 데이터
//        if(holder.javaClass == SendViewHolder::class.java){
//            val viewHolder = holder as SendViewHolder
//            viewHolder.sendMessage.text = currentMessage.toString()
//        }else{//받는 데이터
//            val viewHolder = holder as ReceiveViewHolder
//            viewHolder.receiveMessage.text = currentMessage.toString()
//        }
//    }
//
//    override fun getItemCount(): Int = chatList.size
//    override fun getItemViewType(position: Int): Int {
//
//        //메시지값
//        val currentMessage = chatList[position]
//
//        return if(1 == currentMessage.sendId?.toInt()){
//            send
//        }else{
//            receive
//        }
//    }
//
//    class SendViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
//        val sendMessage: TextView = itemView.findViewById(R.id.tv_itemuserm_title)
//    }
//    inner class SendViewHolder(private val binding: ItemUsermessageBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(chat: ChatItem) {
//            val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
//            val date = Date(chat.time)
//            binding.messageTextView.text = chat.message
//            binding.timeTextView.text = dateFormat.format(date)
//        }
//    }
//
//    class ReceiveViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
//        val receiveMessage: TextView = itemView.findViewById(R.id.tv_itemm_title)
//    }
//}

interface MessageModel {
    data class SenderMessage(
        val message: String
    ): MessageModel

    data class ReceiverMessage(
        val message: String
    ): MessageModel
}

class MessageAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val itemList = arrayListOf<MessageModel>()

    fun addItem(item: MessageModel){
        itemList.add(item)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_SENDER -> SenderViewHolder.create(parent)
            else -> ReceiverViewHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is SenderViewHolder -> holder.bindItem(itemList[position] as MessageModel.SenderMessage)
            is ReceiverViewHolder -> holder.bindItem(itemList[position] as MessageModel.ReceiverMessage)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(itemList[position]){
            is MessageModel.SenderMessage -> TYPE_SENDER
            is MessageModel.SenderMessage -> TYPE_RECEIVER
            else -> -1
        }

    }
    companion object{
        const val TYPE_SENDER = 0
        const val TYPE_RECEIVER = 1
    }

    class ReceiverViewHolder(private val binding: ItemChatmessageBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindItem(receiverMessage: MessageModel.ReceiverMessage){
            binding.tvItemmTitle.text = receiverMessage.message
        }

        companion object {
            fun create(parent: ViewGroup): ReceiverViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ItemChatmessageBinding.inflate(layoutInflater, parent, false)
                return ReceiverViewHolder(view)
            }
        }
    }

    class SenderViewHolder(private val binding: ItemUsermessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(senderMessage: MessageModel.SenderMessage) {
            binding.tvItemusermTitle.text = senderMessage.message
        }

        companion object {
            fun create(parent: ViewGroup): SenderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ItemUsermessageBinding.inflate(layoutInflater, parent, false)
                return SenderViewHolder(view)
            }
        }

    }

}