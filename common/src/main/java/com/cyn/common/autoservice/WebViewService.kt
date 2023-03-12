package com.cyn.common.autoservice

import android.content.Context

interface WebViewService {
    fun startWebViewActivity(context: Context, url: String)
}