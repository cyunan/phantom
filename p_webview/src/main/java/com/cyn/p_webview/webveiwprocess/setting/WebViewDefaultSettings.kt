package com.cyn.p_webview.webveiwprocess.setting

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView

object WebViewDefaultSettings {
    //    private var mWebSettings: WebSettings? = null
    private fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo?.isConnected ?: false
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun setSettings(webView: WebView) {
        WebView.enableSlowWholeDocumentDraw()
        var mWebSettings = webView.settings
        mWebSettings.javaScriptEnabled = true
        mWebSettings.setSupportZoom(true)
        mWebSettings.builtInZoomControls = false
        if (isNetworkConnected(webView.context)) {
            mWebSettings.cacheMode = WebSettings.LOAD_DEFAULT
        } else {
            mWebSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        }
        mWebSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        mWebSettings.textZoom = 100
        mWebSettings.databaseEnabled = true
        mWebSettings.loadsImagesAutomatically = true
        mWebSettings.setSupportMultipleWindows(false)
        mWebSettings.blockNetworkImage = false //是否阻塞加载网络图片  协议http or https
        mWebSettings.allowFileAccess = true //允许加载本地文件html  file协议
        mWebSettings.javaScriptCanOpenWindowsAutomatically = true
        mWebSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        mWebSettings.loadWithOverviewMode = true
        mWebSettings.useWideViewPort = true
        mWebSettings.domStorageEnabled = true
        mWebSettings.setNeedInitialFocus(true)
        mWebSettings.defaultTextEncodingName = "utf-8" //设置编码格式
        mWebSettings.defaultFontSize = 16
        mWebSettings.minimumFontSize = 10 //设置 WebView 支持的最小字体大小，默认为 8
        mWebSettings.setGeolocationEnabled(true)
        mWebSettings.useWideViewPort = true
        val appCacheDir = webView.context.getDir("cache", Context.MODE_PRIVATE).path
        Log.i("WebSetting", "WebView cache dir: $appCacheDir")
        // 用户可以自己设置useragent
        WebView.setWebContentsDebuggingEnabled(true)
    }
}