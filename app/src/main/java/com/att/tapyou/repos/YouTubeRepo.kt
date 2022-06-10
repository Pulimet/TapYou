package com.att.tapyou.repos

import com.att.tapyou.network.services.YouTubeService

class YouTubeRepo(private val youTubeService: YouTubeService) {
    suspend fun getVideos(query: String) = youTubeService.getVideos(query)
}