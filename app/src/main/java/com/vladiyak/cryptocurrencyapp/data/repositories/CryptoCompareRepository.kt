package com.vladiyak.cryptocurrencyapp.data.repositories

import com.vladiyak.cryptocurrencyapp.api.client.CryptoCompareClient
import com.vladiyak.cryptocurrencyapp.model.NewsData

class CryptoCompareRepository {

    val api = CryptoCompareClient.api

    suspend fun getLatestNews(): List<NewsData>? {
        val response = api.getLatestNews()
        return response.body()?.data
    }
}