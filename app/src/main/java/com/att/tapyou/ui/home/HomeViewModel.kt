package com.att.tapyou.ui.home

import androidx.lifecycle.viewModelScope
import com.att.tapyou.repos.YouTubeRepo
import com.att.tapyou.ui.base.BaseViewModel
import com.att.tapyou.utils.logs.logD
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

    fun onQueryTextSubmit(query: String) {
        logD("query: $query")
        fetchVideosIdsList(query)
    }

    private fun fetchVideosIdsList(query: String = "Banana") {
        viewModelScope.launch(ioCoroutineContext) {
            val list = youTubeRepo.getVideos(query)
            _videoIdsList.value = list
        }
    }
}