package com.cyn.phantom.webcommand

import android.content.ComponentName
import android.content.Intent
import android.text.TextUtils
import com.cyn.base.BaseApplication
import com.cyn.p_webview.ICallbackFromMainprocessToWebViewProcessInterface
import com.cyn.p_webview.command.Command
import com.google.auto.service.AutoService

@AutoService(Command::class)
class CommandOpenPage : Command{
    override fun name(): String {
        return "openPage"
    }

    override fun execute(
        parameters: Map<*, *>?,
        callback: ICallbackFromMainprocessToWebViewProcessInterface?
    ) {
        val targetClass = parameters?.get("target_class").toString()
        if (!TextUtils.isEmpty(targetClass)) {
            val intent = Intent()
            intent.component =
                ComponentName(BaseApplication.sApplication, targetClass)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            BaseApplication.sApplication.startActivity(intent)
        }
    }

}