package com.att.tapyou.ui.home

import androidx.lifecycle.viewModelScope
import com.att.tapyou.repos.YouTubeRepo
import com.att.tapyou.ui.base.BaseViewModel
import com.att.tapyou.utils.logs.logD
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeViewModel(
    private val youTubeRepo: YouTubeRepo,
    private val ioCoroutineContext: CoroutineContext
) : BaseViewModel() {

    @Suppress("BlockingMethodInNonBlockingContext") // IO dispatcher used
    fun testIt() {
        viewModelScope.launch(ioCoroutineContext) {
            val result = youTubeRepo.getVideos("Queen").string()
            logD("Result: ${result.substring(0, 100)}")

            Regex("\\?v=.{1,11}\"").findAll(result).forEach { matchResult ->
                logD("ID: ${matchResult.value.substring(3, matchResult.value.length - 1)}")
            }
        }
    }

}