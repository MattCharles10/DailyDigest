package com.example.dailydigest.data.remote.api

import com.example.dailydigest.data.remote.models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("category") category: String? = null,
        @Query("pageSize") pageSize: Int = 20,
        @Query("apiKey") apiKey: String = "864b2fbac493428b8bec08841d5dc18f" // Replace with your actual key
    ): NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("pageSize") pageSize: Int = 20,
        @Query("apiKey") apiKey: String = "864b2fbac493428b8bec08841d5dc18f" // Replace with your actual key
    ): NewsResponse
}