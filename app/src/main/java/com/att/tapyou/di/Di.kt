package com.att.tapyou.di

import android.content.Context
import com.att.tapyou.utils.KoinLogs
import com.att.tapyou.utils.logE
import com.att.tapyou.utils.logI
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import kotlin.system.measureTimeMillis

object Di {
    fun setup(applicationContext: Context) {
        val timeInMillis = measureTimeMillis {
            startKoin {
                logger(KoinLogs())
                androidContext(applicationContext)
                modules(appModule)
            }
        }
        logI("=== DI is ready (timeInMillis: $timeInMillis)===")
    }

    private val appModule = module {
        // CoroutineContext
        single {
            Dispatchers.IO + CoroutineExceptionHandler { _, e ->
                logE("CoroutineExceptionHandler:", t = e)
                throw e
            }
        }
    }
}