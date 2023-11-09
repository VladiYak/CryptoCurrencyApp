package com.vladiyak.cryptocurrencyapp.domain.repository

import com.vladiyak.cryptocurrencyapp.data.network.newsapi.dto.NewsResponseDto
import com.vladiyak.cryptocurrencyapp.domain.models.NewsData

interface CryptoCompareRepository {

    suspend fun getLatestNews(): NewsResponseDto
}