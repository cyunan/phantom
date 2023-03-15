package com.cyn.phantom

import android.text.format.Time
import com.cyn.base.BaseApplication
import com.cyn.base.loadsir.EmptyCallback
import com.cyn.base.loadsir.ErrorCallback
import com.cyn.base.loadsir.LoadingCallback
import com.cyn.base.loadsir.TimeoutCallback
import com.kingja.loadsir.core.LoadSir

class MyApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        LoadSir.beginBuilder()
            .addCallback(ErrorCallback())
            .addCallback(EmptyCallback())
            .addCallback(LoadingCallback())
            .addCallback(TimeoutCallback())
            .setDefaultCallback(LoadingCallback::class.java)
            .commit()
    }
}