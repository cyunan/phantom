package com.cyn.p_webview.webveiwprocess

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import com.cyn.p_webview.WebViewCallBack
import com.cyn.p_webview.WebViewProcessCommandsDispatcher
import com.cyn.p_webview.bean.JsParam
import com.cyn.p_webview.webveiwprocess.setting.WebViewDefaultSettings
import com.cyn.p_webview.webveiwprocess.webchromeclient.BaseWebChromeClient
import com.cyn.p_webview.webveiwprocess.webviewclient.BaseWebViewClient
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


class BaseWebView : WebView {
    val moshi by lazy { Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build() }
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

    private fun init() {
        WebViewProcessCommandsDispatcher.initAidlConnection()
        addJavascriptInterface(this, "sdkwebview")
        WebViewDefaultSettings.setSettings(this)
    }

    fun registerWebViewCallBack(webViewCallBack: WebViewCallBack?) {
        webViewClient = BaseWebViewClient(webViewCallBack)
        webChromeClient = BaseWebChromeClient(webViewCallBack)

    }

    @JavascriptInterface
    fun takeNativeAction(jsParams: String){
        Log.e("TAG",jsParams)
        if (jsParams.isNotEmpty()){
            val json = moshi.adapter(JsParam::class.java).fromJson(jsParams)
            json?.let { WebViewProcessCommandsDispatcher
                .executeCommand(json.name, moshi.adapter(Map::class.java).toJson(it.param), this) }
        }

    }

    fun handleCallback(callbackName: String?, response: String?){
        if ((callbackName?.isNotEmpty() == true) && (response?.isNotEmpty() == true)){
            post{
                val jsCode = "javascript:xiangxuejs.callback('$callbackName',$response)"
                evaluateJavascript(jsCode, null)
            }
        }
    }


    companion object {
        const val TAG = "BaseWebView"
    }
}

fun main() {

}