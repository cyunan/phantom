package com.cyn.phantom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.cyn.common.autoservice.IUserCenterService
import com.cyn.common.autoservice.WebViewService
import com.cyn.common.utils.IServiceLoader

class MainActivity : AppCompatActivity() {
    private val mWebViewService by lazy{ IServiceLoader.load(WebViewService::class.java) }
    private val iUserCenterService by lazy { IServiceLoader.load(IUserCenterService::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_run).setOnClickListener {
            mWebViewService?.startWebViewActivity(this, "https://www.baidu.com","title", true)
        }
        findViewById<Button>(R.id.btn_demo).setOnClickListener {
            mWebViewService?.startWebViewActivity(this, "file:///android_asset/demo.html","title", true)
        }
    }
}