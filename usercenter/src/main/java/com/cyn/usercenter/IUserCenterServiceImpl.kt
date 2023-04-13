package com.cyn.usercenter

import android.content.Intent
import com.cyn.base.BaseApplication
import com.cyn.common.autoservice.IUserCenterService
import com.google.auto.service.AutoService

@AutoService(IUserCenterService::class)
class IUserCenterServiceImpl : IUserCenterService {
    override fun isLogined(): Boolean {
        return false
    }

    override fun login() {
        val intent = Intent(BaseApplication.sApplication, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        BaseApplication.sApplication.startActivity(intent)
    }
}