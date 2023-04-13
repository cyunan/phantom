package com.cyn.usercenter

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.cyn.common.eventbus.LoginEvent
import com.google.android.material.textfield.TextInputLayout
import org.greenrobot.eventbus.EventBus


class LoginActivity : AppCompatActivity() {
    private var btnLogin: Button? = null
    private var inputEmail: TextInputLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        inputEmail = findViewById<TextInputLayout>(R.id.lTextEmail)
        val lTextPassword = findViewById<TextInputLayout>(R.id.lTextPassword)
        btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnLinkToRegisterScreen = findViewById<Button>(R.id.btnLinkToRegisterScreen)
        val btnForgotPassword = findViewById<Button>(R.id.btnForgotPassword)
        init()
    }
    fun init() {
        btnLogin?.setOnClickListener {
            EventBus.getDefault().post(LoginEvent(inputEmail?.editText?.text.toString()))
            finish()
        }
    }
}