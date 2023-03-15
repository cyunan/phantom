package com.cyn.p_webview

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.cyn.common.autoservice.WebViewService
import com.google.auto.service.AutoService

@AutoService(WebViewService::class)
class WebViewServiceImpl : WebViewService{
    override fun startWebViewActivity(context: Context, url: String, title: String, isShowActionbar: Boolean) {
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra(WebViewActivity.WEBVIEW_ACTIVITY_URL, url)
        intent.putExtra(WebViewActivity.WEBVIEW_ACTIVITY_Title, title)
        intent.putExtra(WebViewActivity.WEBVIEW_ACTIVITY_IS_SHOW_ACTIONBAR, isShowActionbar)
        context.startActivity(intent)
    }

    override fun getWebViewFragment(url: String, canNativeRefresh: Boolean): Fragment {
        return WebViewFragment.newInstance(url, canNativeRefresh)
    }

}