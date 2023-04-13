package com.cyn.webview

interface IWebviewProcessToMainProcessInterface {
    fun handleWebCommand(commandName: String, jsonParams: String)
}