package com.att.tapyou.utils.logs

import okhttp3.logging.HttpLoggingInterceptor

@Suppress("ConstantConditionIf")
class OkHttpLogs : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        logI(message)
    }
}