package com.cyn.p_webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.cyn.p_webview.databinding.ActivityWebviewBinding

class WebViewActivity : AppCompatActivity() {
    private var mBinding :ActivityWebviewBinding? = null
    private var mUri : String = ""
    companion object{
        const val URL = "url"
        const val TAG = "WebViewActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_webview)
        mUri = intent?.extras?.getString(URL) ?: "https://www.baidu.com"
        mBinding?.webview?.loadUrl(mUri)
        mBinding?.webview?.settings?.javaScriptEnabled = true
        Log.e(TAG, "url: ${intent.extras?.getString(URL).toString()}")

    }
}