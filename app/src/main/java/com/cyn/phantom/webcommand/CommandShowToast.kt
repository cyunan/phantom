package com.cyn.phantom.webcommand

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.cyn.base.BaseApplication
import com.cyn.p_webview.ICallbackFromMainprocessToWebViewProcessInterface
import com.cyn.p_webview.command.Command
import com.google.auto.service.AutoService

@AutoService(Command::class)
class CommandShowToast : Command{
    override fun name(): String {
        return "showToast"
    }

    override fun execute(
        parameters: Map<*, *>?,
        callback: ICallbackFromMainprocessToWebViewProcessInterface?
    ) {
        val handler = Handler(Looper.getMainLooper())
        handler.post {
            Toast.makeText(
                BaseApplication.sApplication,
                parameters?.get("message").toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}