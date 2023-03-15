package com.cyn.phantom

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.cyn.common.autoservice.WebViewService
import com.cyn.common.utils.IServiceLoader

class MainActivity : AppCompatActivity() {
    private val mWebViewService by lazy{ IServiceLoader.load(WebViewService::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_run).setOnClickListener {
            mWebViewService?.startWebViewActivity(this, "https://www.baidu.com","title", true)
        }
    }
}