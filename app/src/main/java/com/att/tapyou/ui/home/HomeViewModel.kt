package com.att.tapyou.ui.home

import androidx.lifecycle.viewModelScope
import com.att.tapyou.repos.YouTubeRepo
import com.att.tapyou.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeViewModel(
    private val youTubeRepo: YouTubeRepo,
    private val ioCoroutineContext: CoroutineContext
) : BaseViewModel() {

    private val _videoIdsList = MutableStateFlow<List<String>>(emptyList())
    val videoIdsList: StateFlow<List<String>> = _videoIdsList


    fun onViewCreated() {
        fetchVideosIdsList()
    }

    private fun fetchVideosIdsList() {
        viewModelScope.launch(ioCoroutineContext) {
            val list = youTubeRepo.getVideos("Queen")
            _videoIdsList.value = list
        }
    }

}