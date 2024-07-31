package com.example.chatapptask

import android.content.Context
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MessageAdapter(mcontext:Context,mymutableList: MutableList<String>) : RecyclerView.Adapter<MessageAdapter.ViewHolder>()  {
    var MSG_TYPE_LEFT:Int =0
    var MSG_TYPE_Right:Int =1
    var context = mcontext

    var mymutableList = mymutableList

    //maybe constructor here im not sure


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.profile_image)
        val textView: TextView = itemView.findViewById(R.id.recyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == MSG_TYPE_Right){
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_item_right, parent, false)
        return ViewHolder(view)
        }
        else
        {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_item_left, parent, false)
            return ViewHolder(view)

        }
    }

    override fun getItemCount(): Int {

        return mymutableList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var chat = mymutableList.get(position)
        holder.textView.text = chat    }
}