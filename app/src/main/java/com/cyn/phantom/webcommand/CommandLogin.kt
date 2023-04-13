package com.cyn.phantom.webcommand

import android.util.Log
import com.cyn.common.autoservice.IUserCenterService
import com.cyn.common.eventbus.LoginEvent
import com.cyn.common.utils.IServiceLoader
import com.cyn.p_webview.ICallbackFromMainprocessToWebViewProcessInterface
import com.cyn.p_webview.command.Command
import com.google.auto.service.AutoService
import com.squareup.moshi.Moshi
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

@AutoService(Command::class)
class CommandLogin :Command{
    private val iUserCenterService = IServiceLoader.load(IUserCenterService::class.java)
    private var mCallback: ICallbackFromMainprocessToWebViewProcessInterface? = null
    private var callbackNameFromNativeJs: String? = null

    init {
        EventBus.getDefault().register(this)
    }
    override fun name(): String {
        return "login"
    }

    override fun execute(
        parameters: Map<*, *>?,
        callback: ICallbackFromMainprocessToWebViewProcessInterface?
    ) {
        Log.d("CommandLogin", Moshi.Builder().build().adapter(Map::class.java).toJson(parameters))
        if (iUserCenterService?.isLogined != true){
            iUserCenterService?.login()
            mCallback = callback //app process callback webview process
            callbackNameFromNativeJs = parameters?.get("callbackname").toString()
        }
    }


    fun commandLogin() {
        EventBus.getDefault().register(this)
    }

    @Subscribe
    fun onMessageEvent(event: LoginEvent) {
        Log.d("CommandLogin", event.userName)
        val map = mutableMapOf<String,String>()
        map["accountName"] = event.userName
        mCallback?.onResult(callbackNameFromNativeJs, Moshi.Builder().build().adapter(Map::class.java).toJson(map))
    }
}