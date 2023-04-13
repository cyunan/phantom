package com.cyn.p_webview.command

import com.cyn.p_webview.ICallbackFromMainprocessToWebViewProcessInterface

interface Command {
    fun name(): String
    fun execute(
        parameters: Map<*, *>?,
        callback: ICallbackFromMainprocessToWebViewProcessInterface?
    )
}