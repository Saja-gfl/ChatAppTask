package com.example.chatapptask

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hbb20.CountryCodePicker

class LoginPhoneNumberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_phone_number)
        var countryCodePicker = findViewById<CountryCodePicker>(R.id.logincpp)
        var sendotpbtn = findViewById<Button>(R.id.login_otp_btn)
        var phoneinput = findViewById<EditText>(R.id.login_mobile_number)
        var progbar = findViewById<ProgressBar>(R.id.login_progbar)
        progbar.visibility = View.GONE
        countryCodePicker.registerCarrierNumberEditText(phoneinput)
        sendotpbtn.setOnClickListener{task ->
            if(!countryCodePicker.isValidFullNumber) {
                phoneinput.setError("not a full number")
//                return@setOnClickListener
            }
            intent = Intent(this,LoginOtpActivity::class.java)
            intent.putExtra("phone",countryCodePicker.fullNumberWithPlus)
            startActivity(intent)

        }



    }
}