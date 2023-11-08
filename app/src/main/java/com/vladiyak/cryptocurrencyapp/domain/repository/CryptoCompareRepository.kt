package com.vladiyak.cryptocurrencyapp.domain.repository

import com.vladiyak.cryptocurrencyapp.domain.model.NewsData

interface CryptoCompareRepository {

    suspend fun getLatestNews(): List<NewsData>?
}