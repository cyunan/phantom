package com.cyn.p_webview

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.cyn.p_webview.databinding.ActivityWebviewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var mBinding:ActivityWebviewBinding
    private var mUri : String = ""
    companion object{
        const val WEBVIEW_ACTIVITY_URL = "url"
        const val WEBVIEW_ACTIVITY_Title = "title"
        const val WEBVIEW_ACTIVITY_IS_SHOW_ACTIONBAR = "is_show_actionbar"
        const val TAG = "WebViewActivity"
    }

    @RequiresApi(Build.VERSION_CODES.M)
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
        fillNorchAreaColor(this, mBinding.clLayout, Color.parseColor("#7fFFFFFF"))
        getBackgroundColor(mBinding.clLayout)

    }

    fun updateTitle(title: String?) {
        mBinding.tvTitle.text = title
    }

    fun fillNorchAreaColor(activity: Activity, rootView: View, color: Int) {
        //是否适配刘海屏
        val adapt: Boolean = true
        if (adapt && Build.VERSION.SDK_INT >= 28) {
            val window = activity.window
            adaptDisplayCutoutMode(window)
            val contentView = activity.findViewById<View>(android.R.id.content) as FrameLayout
            contentView.setBackgroundColor(color)
            window.decorView.setOnApplyWindowInsetsListener { view, windowInsets ->
                if (windowInsets != null) {
                    val displayCutout = windowInsets.displayCutout
                    if (displayCutout != null) {
                        rootView.setPadding(
                            displayCutout.safeInsetLeft, displayCutout.safeInsetTop,
                            displayCutout.safeInsetRight, displayCutout.safeInsetBottom
                        )
                    }
                }
                windowInsets.consumeSystemWindowInsets()
            }
        }
    }
    fun adaptDisplayCutoutMode(window: Window?){
        setDisplayCutoutMode(window, 1)
    }
    fun setDisplayCutoutMode(window: Window?, mode: Int) {
        if (window != null && VERSION.SDK_INT >= 28 && mode >= 0) {
            val params = window.attributes
            params.layoutInDisplayCutoutMode = mode
            window.attributes = params
        }
    }
    fun getBackgroundColor(view: View): Int {
        var bgColor = Color.parseColor("#000000")
        if (view.background is ColorDrawable) {
            val colorDrawable = view.background as ColorDrawable
            bgColor = colorDrawable.color
        }
        return bgColor
    }

}