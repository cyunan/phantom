package com.cyn.webview

interface ICallbackFromMainprocessToWebViewProcessInterface {
    fun onResult(callbackname: String, response: String)
}