package com.cyn.p_webview.webveiwprocess

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.webkit.JavascriptInterface
import android.webkit.WebView
import com.cyn.p_webview.WebViewCallBack
import com.cyn.p_webview.webveiwprocess.webchromeclient.BaseWebChromeClient
import com.cyn.p_webview.webveiwprocess.webviewclient.BaseWebViewClient


class BaseWebView : WebView {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    fun init() {

    }

    fun registerWebViewCallBack(webViewCallBack: WebViewCallBack?) {
        webViewClient = BaseWebViewClient(webViewCallBack)
        webChromeClient = BaseWebChromeClient(webViewCallBack)
    }


    companion object {
        const val TAG = "BaseWebView"
    }
}