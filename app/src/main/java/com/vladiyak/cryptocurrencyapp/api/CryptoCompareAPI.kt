package com.vladiyak.cryptocurrencyapp.api

import com.vladiyak.cryptocurrencyapp.model.responses.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface CryptoCompareAPI {
    @GET("news/")
    suspend fun getLatestNews(): Response<NewsResponse>

}

