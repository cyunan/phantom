package com.cyn.p_webview

import android.content.Context
import android.content.Intent
import com.cyn.common.autoservice.WebViewService
import com.google.auto.service.AutoService

@AutoService(WebViewService::class)
class WebViewServiceImpl : WebViewService{
    override fun startWebViewActivity(context: Context, url: String) {
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra(WebViewActivity.URL, url)
        context.startActivity(intent)
    }

}