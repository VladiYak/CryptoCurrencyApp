package com.vladiyak.cryptocurrencyapp.data.repositories

import com.vladiyak.cryptocurrencyapp.data.api.CryptoCompareAPI
import com.vladiyak.cryptocurrencyapp.domain.model.NewsData
import com.vladiyak.cryptocurrencyapp.domain.repository.CryptoCompareRepository
import javax.inject.Inject

class CryptoCompareRepositoryImpl @Inject constructor(
    private val cryptoCompareAPI: CryptoCompareAPI
): CryptoCompareRepository {


    override suspend fun getLatestNews(): List<NewsData>? {
        val response = cryptoCompareAPI.getLatestNews()
        return response.body()?.data
    }
}