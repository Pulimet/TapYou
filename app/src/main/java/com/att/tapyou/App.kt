package com.att.tapyou

import android.app.Application
import com.att.tapyou.di.Di
import com.att.tapyou.utils.logs.logI

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        logI("=== Application onCreate() ===")
        Di.setup(applicationContext)
    }
}