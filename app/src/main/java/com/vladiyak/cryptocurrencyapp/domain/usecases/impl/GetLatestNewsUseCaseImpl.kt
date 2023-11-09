package com.vladiyak.cryptocurrencyapp.domain.usecases.impl

import com.vladiyak.cryptocurrencyapp.data.network.newsapi.dto.toNewsResponse
import com.vladiyak.cryptocurrencyapp.domain.models.NewsData
import com.vladiyak.cryptocurrencyapp.domain.repository.CryptoCompareRepository
import com.vladiyak.cryptocurrencyapp.domain.usecases.GetLatestNewsUseCase
import javax.inject.Inject

class GetLatestNewsUseCaseImpl @Inject constructor(
    private val repository: CryptoCompareRepository,
): GetLatestNewsUseCase {

    override suspend operator fun invoke(): List<NewsData>? {
        val response = repository.getLatestNews()
        return response.toNewsResponse().data
    }
}