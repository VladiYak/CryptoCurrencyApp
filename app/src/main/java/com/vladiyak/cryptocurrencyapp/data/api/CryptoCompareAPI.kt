package com.vladiyak.cryptocurrencyapp.data.api

import com.vladiyak.cryptocurrencyapp.domain.model.responses.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface CryptoCompareAPI {
    @GET("news/")
    suspend fun getLatestNews(): Response<NewsResponse>

}

