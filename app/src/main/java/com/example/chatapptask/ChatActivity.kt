package com.example.chatapptask

import ChatRoomDataClass
import android.annotation.SuppressLint
import android.content.Context
import android.media.Image
import android.os.Bundle
import android.os.Message
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import android.view.ViewManager
import android.widget.Adapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.internal.Objects
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chat)

        var messageInput = findViewById<EditText>(R.id.chat_message_input)
        var sendmsgbtn = findViewById<ImageButton>(R.id.msg_send_btn)
        var backbtn = findViewById<ImageButton>(R.id.backbtn)
        var otherUserName = findViewById<TextView>(R.id.username_txtview)
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val chatmutableList : MutableList<String> = arrayListOf()
//        recyclerView.adapter = MessageAdapter(mcontext = this, mymutableList = chatmutableList)

        sendmsgbtn.setOnClickListener {
            var msg : String = messageInput.text.toString()
            if(!msg.equals(""))
            {
                sendMsg( "1" , "2" , msg)
            }
            else {
                Toast.makeText(this,"you cant send",Toast.LENGTH_SHORT).show()
            }
            messageInput.setText("")
        }
        fun showmsg(Sender: String,reciver: String){

            var dbref = FirebaseDatabase.getInstance().getReference("Chats")
            dbref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot){
                    chatmutableList.clear()
                    for(dataSnapshot in dataSnapshot.children){
                        dataSnapshot.getValue()
                        chatmutableList.add(dataSnapshot.toString())
                    }

                    recyclerView.adapter = MessageAdapter(mcontext = this@ChatActivity, mymutableList = chatmutableList)
                   var mLayoutManager =  LinearLayoutManager(this@ChatActivity);
                    recyclerView.setLayoutManager(mLayoutManager)
                }
                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException())
                }
            })


        }

        showmsg("1" , "2")
    }


    private fun sendMsg(Sender:String, reciver:String, msg:String){
        var dbref = FirebaseDatabase.getInstance().getReference()
    var hashMap : HashMap<String, String> = HashMap<String, String> ()

    hashMap.put("sender",Sender)
    hashMap.put("reciver",reciver)
    hashMap.put("msg",msg)
    dbref.child("Chats").push().setValue(hashMap)

}
}
