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

    fun testIt() {
        viewModelScope.launch(ioCoroutineContext) {
            val result = youTubeRepo.getVideos("Queen")
            logD("Result: ${result.string().substring(0, 100)}")
        }
    }

}