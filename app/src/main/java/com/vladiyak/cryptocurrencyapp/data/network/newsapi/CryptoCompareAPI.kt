package com.vladiyak.cryptocurrencyapp.data.network.newsapi

import com.vladiyak.cryptocurrencyapp.data.network.newsapi.dto.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface CryptoCompareAPI {
    @GET("news/")
    suspend fun getLatestNews(): Response<NewsResponse>

}

