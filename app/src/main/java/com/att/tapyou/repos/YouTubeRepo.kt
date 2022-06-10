package com.att.tapyou.repos

import com.att.tapyou.network.services.YouTubeService

class YouTubeRepo(private val youTubeService: YouTubeService) {

    @Suppress("BlockingMethodInNonBlockingContext") // IO dispatcher used
    suspend fun getVideos(query: String): List<String> {
        val htmlPage = youTubeService.getVideos(query).string()
        val videoIdsList = Regex("\\?v=.{1,11}\"").findAll(htmlPage).mapTo(ArrayList()) {
            it.value.substring(3, it.value.length - 1)
        }
        return videoIdsList.toList()
    }

    /*Regex("\\?v=.{1,11}\"").findAll(htmlPage).forEach { matchResult ->
        logD("ID: ${matchResult.value.substring(3, matchResult.value.length - 1)}")
    }*/

}