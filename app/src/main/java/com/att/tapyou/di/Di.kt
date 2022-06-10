package com.att.tapyou.di

import android.content.Context
import com.att.tapyou.network.NetworkConstants
import com.att.tapyou.network.NetworkObjectsCreator
import com.att.tapyou.network.NetworkObjectsCreator.createWebService
import com.att.tapyou.network.services.YouTubeService
import com.att.tapyou.repos.YouTubeRepo
import com.att.tapyou.ui.home.HomeViewModel
import com.att.tapyou.ui.navigation.NavViewModel
import com.att.tapyou.utils.logs.KoinLogs
import com.att.tapyou.utils.logs.OkHttpLogs
import com.att.tapyou.utils.logs.logE
import com.att.tapyou.utils.logs.logI
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import kotlin.system.measureTimeMillis

object Di {
    fun setup(applicationContext: Context) {
        val timeInMillis = measureTimeMillis {
            startKoin {
                logger(KoinLogs())
                androidContext(applicationContext)
                modules(appModule, networkModule)
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

        // ViewModels
        viewModelOf(::NavViewModel)
        viewModelOf(::HomeViewModel)

        // Repos
        singleOf(::YouTubeRepo)
    }

    private val networkModule = module {
        single<HttpLoggingInterceptor.Logger> { OkHttpLogs() }
        single { NetworkObjectsCreator.createOkHttpClient(get()) }
        single { createWebService<YouTubeService>(get(), NetworkConstants.YOUTUBE_BASE_URL) }
    }
}