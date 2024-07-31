package com.example.chatapptask

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthOptions.Builder
import com.google.firebase.auth.PhoneAuthOptions.newBuilder
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.sql.Time
import java.util.concurrent.TimeUnit

class LoginOtpActivity : AppCompatActivity() {







    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_otp)
        lateinit var database: DatabaseReference
        var progbar = findViewById<ProgressBar>(R.id.login_progbar)
        var nextbtn = findViewById<Button>(R.id.next_btn)
        lateinit var verficationcode: String
        lateinit var token: PhoneAuthProvider.ForceResendingToken
        var otpEdittext = findViewById<EditText>(R.id.otp_)
        var resendotp = findViewById<TextView>(R.id.resendOTP_tv)
        lateinit var mauth: FirebaseAuth
        var PhoneNumber: String = intent.getStringExtra("phone").toString()

        database = FirebaseDatabase.getInstance().getReference()
        mauth = FirebaseAuth.getInstance()
        fun setInProgress(inprog: Boolean) {
            if (inprog) {
                progbar.setVisibility(View.VISIBLE)
                progbar.setVisibility(View.GONE)
            } else {
                progbar.setVisibility(View.GONE)
                progbar.setVisibility(View.VISIBLE)
            }
        }

        fun singin(phoneAuthCredential: PhoneAuthCredential) {
            //log in and go to next activity

        }


        fun sendotp(phonenumber: String, isResend: Boolean) {
            setInProgress(true)
            var callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    singin(credential)
                    setInProgress(false)
                    Toast.makeText(applicationContext, "this is Completed", Toast.LENGTH_LONG)
                        .show()

                }

                override fun onVerificationFailed(e: FirebaseException) {
                    Toast.makeText(applicationContext, "Failed to verfiy", Toast.LENGTH_LONG).show()
                    setInProgress(false)
                }

                override fun onCodeSent(p0: String, p1: ForceResendingToken) {
                    super.onCodeSent(p0, p1)
                    verficationcode = p0

                    var token:ForceResendingToken = p1
                    Toast.makeText(applicationContext, "OTP sent", Toast.LENGTH_LONG).show()
                    setInProgress(false)

                }
            }

            var builder: PhoneAuthOptions.Builder = PhoneAuthOptions.newBuilder(mauth)
                .setPhoneNumber(phonenumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(callbacks)





            if (isResend) {
                PhoneAuthProvider.verifyPhoneNumber(builder.setForceResendingToken(token).build())
            }
            else
            {
                PhoneAuthProvider.verifyPhoneNumber(builder.build())

            }



        }


        sendotp(PhoneNumber,false)




}}