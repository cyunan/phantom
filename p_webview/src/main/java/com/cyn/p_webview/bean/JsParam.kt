package com.cyn.p_webview.bean


data class JsParam(
    val name: String,
    val param: Map<*,*>
)

data class MessageBean(
    val message: String
)
