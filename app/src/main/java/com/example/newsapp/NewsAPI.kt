package com.example.newsapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("top-headlines?sources=techcrunch")
    suspend fun getNews(@Query("apiKey")key: String): Response<News>
}