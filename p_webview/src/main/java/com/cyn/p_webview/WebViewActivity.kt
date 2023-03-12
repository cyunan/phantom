package com.cyn.p_webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class WebViewActivity : AppCompatActivity() {
    companion object{
        const val URL = "url"
        const val TAG = "WebViewActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        Log.e(TAG, "url: ${intent.extras?.getString(URL).toString()}")
    }
}