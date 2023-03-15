package com.cyn.common.autoservice

import android.content.Context
import androidx.fragment.app.Fragment

interface WebViewService {
    fun startWebViewActivity(
        context: Context,
        url: String,
        title: String,
        isShowActionbar: Boolean)

    fun getWebViewFragment(
        url: String,
        canNativeRefresh: Boolean = true
    ): Fragment
}