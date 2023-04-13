package com.cyn.p_webview

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.webkit.WebView
import com.cyn.base.BaseApplication
import com.cyn.p_webview.mainprocess.MainProcessCommandService
import com.cyn.p_webview.webveiwprocess.BaseWebView

/**
 * 用来进行服务器链接
 */
object WebViewProcessCommandsDispatcher : ServiceConnection{
    private var iWebViewProcessToMainProcessInterface: IWebviewProcessToMainProcessInterface? = null

    fun initAidlConnection(){
        val intent = Intent(BaseApplication.sApplication, MainProcessCommandService::class.java)
        BaseApplication.sApplication.bindService(intent, this, Context.BIND_AUTO_CREATE)
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        iWebViewProcessToMainProcessInterface = IWebviewProcessToMainProcessInterface.Stub.asInterface(service)
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        iWebViewProcessToMainProcessInterface = null
        initAidlConnection()
    }

    fun executeCommand(commandName: String, parameters: String, webView: BaseWebView){
        iWebViewProcessToMainProcessInterface?.handleWebCommand(commandName, parameters, object :
            ICallbackFromMainprocessToWebViewProcessInterface.Stub() {
            override fun onResult(kotlinToJavescriptCallBackName: String?, response: String?) {
                webView.handleCallback(kotlinToJavescriptCallBackName, response)
            }

        })
    }

}