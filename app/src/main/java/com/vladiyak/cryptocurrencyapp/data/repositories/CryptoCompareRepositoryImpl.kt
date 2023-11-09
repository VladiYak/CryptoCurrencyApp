package com.vladiyak.cryptocurrencyapp.data.repositories

import com.vladiyak.cryptocurrencyapp.data.network.newsapi.CryptoCompareApiService
import com.vladiyak.cryptocurrencyapp.data.network.newsapi.dto.NewsResponseDto
import com.vladiyak.cryptocurrencyapp.data.network.newsapi.mappers.NewsResponseDtoMapper
import com.vladiyak.cryptocurrencyapp.domain.models.NewsData
import com.vladiyak.cryptocurrencyapp.domain.repository.CryptoCompareRepository
import javax.inject.Inject

class CryptoCompareRepositoryImpl @Inject constructor(
    private val cryptoCompareApi: CryptoCompareApiService,
    private val mapper: NewsResponseDtoMapper
): CryptoCompareRepository {


    override suspend fun getLatestNews(): NewsResponseDto {
        return cryptoCompareApi.getLatestNews()
    }
}