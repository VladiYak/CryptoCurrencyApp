package com.vladiyak.cryptocurrencyapp.domain.repository

import com.vladiyak.cryptocurrencyapp.data.network.newsapi.dto.NewsData

interface CryptoCompareRepository {

    suspend fun getLatestNews(): List<NewsData>?
}