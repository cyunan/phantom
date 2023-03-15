package com.cyn.p_webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.cyn.base.loadsir.ErrorCallback
import com.cyn.base.loadsir.LoadingCallback
import com.cyn.p_webview.databinding.FragmentWebviewBinding
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener


class WebViewFragment : Fragment(), OnRefreshListener, WebViewCallBack{
    private var mUrl: String = ""
    private var mCanNativeRefresh: Boolean = false
    private lateinit var mBinding: FragmentWebviewBinding
    private lateinit var mLoadService: LoadService<*>
    private var mIsError: Boolean = false
    companion object{
        const val WEBVIEW_Fragment_URL = "url"
        const val WEBVIEW_FRAGMENT_CAN_NATIVE_REFRESH = "canNativeRefresh"
        const val TAG = "WebViewActivity"
        fun newInstance(url: String, canNativeRefresh: Boolean): WebViewFragment{
            val fragment = WebViewFragment()
            val bundle = Bundle().apply {
                putString(WEBVIEW_Fragment_URL, url)
                putBoolean(WEBVIEW_FRAGMENT_CAN_NATIVE_REFRESH, canNativeRefresh)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            mUrl = bundle.getString(WEBVIEW_Fragment_URL) ?: ""
            mCanNativeRefresh = bundle.getBoolean(WEBVIEW_FRAGMENT_CAN_NATIVE_REFRESH)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview, container, false)
        mBinding.webview.registerWebViewCallBack(this)
        mBinding.webview.loadUrl(mUrl)
        mLoadService = LoadSir.getDefault().register(mBinding.smartFreshLayout){
            mLoadService.showCallback(LoadingCallback::class.java)
            mBinding.webview.settings.javaScriptEnabled = true
            mBinding.webview.reload()

        }
        mBinding.smartFreshLayout.setOnRefreshListener(this)
        mBinding.smartFreshLayout.setEnableRefresh(mCanNativeRefresh)
        mBinding.smartFreshLayout.setEnableLoadMore(false)
        return mLoadService.loadLayout
    }

    override fun pageStarted(url: String?) {
        mLoadService.showCallback(LoadingCallback::class.java)
    }

    override fun pageFinished(url: String?) {
        if (mIsError) {
            mBinding.smartFreshLayout.setEnableRefresh(true)
        } else {
            mBinding.smartFreshLayout.setEnableRefresh(mCanNativeRefresh)
        }
        Log.d(TAG, "pageFinished")
        mBinding.smartFreshLayout.finishRefresh()
        if (mLoadService != null) {
            if (mIsError) {
                mLoadService.showCallback(ErrorCallback::class.java)
            } else {
                mLoadService.showSuccess()
            }
        }
        mIsError = false
    }

    override fun onError() {
        Log.e(TAG, "onError")
        mIsError = true
        mBinding.smartFreshLayout.finishRefresh()
    }

    override fun updateTitle(title: String?) {
        if (activity is WebViewActivity) {
            (activity as WebViewActivity).updateTitle(title)
        }
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mBinding.webview.reload()
    }
}