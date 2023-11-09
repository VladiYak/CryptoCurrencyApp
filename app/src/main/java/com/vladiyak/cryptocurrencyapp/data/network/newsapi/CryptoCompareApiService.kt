package com.vladiyak.cryptocurrencyapp.data.network.newsapi

import com.vladiyak.cryptocurrencyapp.data.network.newsapi.dto.NewsResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface CryptoCompareApiService {
    @GET("news/")
    suspend fun getLatestNews(): NewsResponseDto

}

