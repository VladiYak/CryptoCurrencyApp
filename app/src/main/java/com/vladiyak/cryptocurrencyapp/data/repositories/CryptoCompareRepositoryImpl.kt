package com.vladiyak.cryptocurrencyapp.data.repositories

import com.vladiyak.cryptocurrencyapp.data.network.newsapi.CryptoCompareApiService
import com.vladiyak.cryptocurrencyapp.data.network.newsapi.mappers.NewsResponseDtoMapper
import com.vladiyak.cryptocurrencyapp.domain.models.NewsData
import com.vladiyak.cryptocurrencyapp.domain.repository.CryptoCompareRepository
import javax.inject.Inject

class CryptoCompareRepositoryImpl @Inject constructor(
    private val cryptoCompareAPI: CryptoCompareApiService,
    private val mapper: NewsResponseDtoMapper
): CryptoCompareRepository {


    override suspend fun getLatestNews(): List<NewsData>? {
        val response = cryptoCompareAPI.getLatestNews()
        val newsResponse = mapper.mapToDomainModel(response.body())
        return newsResponse.data
    }
}