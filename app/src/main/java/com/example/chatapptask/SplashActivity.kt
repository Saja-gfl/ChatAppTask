package com.example.chatapptask

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
//        intent = Intent(this, LoginPhoneNumberActivity::class.java)
        Handler().postDelayed(Runnable {
            startActivity(Intent(this,ChatActivity::class.java))
            finish()
        },3000)
    }
}