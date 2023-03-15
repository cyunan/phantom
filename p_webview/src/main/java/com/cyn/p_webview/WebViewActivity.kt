package com.cyn.p_webview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.cyn.p_webview.databinding.ActivityWebviewBinding

class WebViewActivity : AppCompatActivity() {
    private var mBinding :ActivityWebviewBinding? = null
    private var mUri : String = ""
    companion object{
        const val WEBVIEW_ACTIVITY_URL = "url"
        const val WEBVIEW_ACTIVITY_Title = "title"
        const val WEBVIEW_ACTIVITY_IS_SHOW_ACTIONBAR = "is_show_actionbar"
        const val TAG = "WebViewActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_webview)
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val fragment: Fragment =
            WebViewFragment.newInstance(
                intent.extras?.getString(WEBVIEW_ACTIVITY_URL).toString(),true)
        transaction.replace(R.id.wb_fragment, fragment).commit()
//        mUri = intent?.extras?.getString(WEBVIEW_ACTIVITY_URL) ?: "https://www.baidu.com"
//        Log.e(TAG, "url: ${intent.extras?.getString(WEBVIEW_ACTIVITY_URL).toString()}")

    }

    fun updateTitle(title: String?) {
        mBinding?.tvTitle?.text = title
    }
}