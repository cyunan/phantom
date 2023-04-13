package com.cyn.p_webview.mainprocess

import android.util.Log
import com.cyn.p_webview.ICallbackFromMainprocessToWebViewProcessInterface
import com.cyn.p_webview.IWebviewProcessToMainProcessInterface
import com.cyn.p_webview.bean.JsParam
import com.cyn.p_webview.command.Command
import com.squareup.moshi.Moshi
import java.util.*
import kotlin.collections.HashMap

/**
 * 主进程命令管理器
 */
object MainProcessCommandsManager : IWebviewProcessToMainProcessInterface.Stub(){
    const val TAG = "MainProcessCommandsManager"
    private val mCommands = mutableMapOf<String, Command>()

    init {
        // 获取所有命令实现类，加载到map里面
        val serviceLoader = ServiceLoader.load(Command::class.java)
        serviceLoader.forEach{ command->
            if (mCommands.containsKey(command.name())) return@forEach
            mCommands[command.name()] = command
        }
    }

    /**
     * 处理所有命令的逻辑
     */
    override fun handleWebCommand(
        commandName: String?,
        jsonParams: String?,
        callback: ICallbackFromMainprocessToWebViewProcessInterface?
    ) {
        Log.e("TAG", "MainProcessCommandsManager handleWebCommand")
        mCommands[commandName]?.execute(
            jsonParams?.let { Moshi.Builder().build().adapter(Map::class.java).fromJson(it) },
            callback
        )
    }

}
