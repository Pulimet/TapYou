package com.att.tapyou.network.services

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeService {
    @GET("results")
    suspend fun getVideos(@Query("search_query") query: String): ResponseBody
}