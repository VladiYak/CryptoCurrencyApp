package com.vladiyak.cryptocurrencyapp.domain.usecases.impl

import com.vladiyak.cryptocurrencyapp.data.network.newsapi.mappers.NewsResponseDtoMapper
import com.vladiyak.cryptocurrencyapp.domain.models.NewsData
import com.vladiyak.cryptocurrencyapp.domain.repository.CryptoCompareRepository
import com.vladiyak.cryptocurrencyapp.domain.usecases.GetLatestNewsUseCase
import javax.inject.Inject

class GetLatestNewsUseCaseImpl @Inject constructor(
    private val repository: CryptoCompareRepository,
    private val mapper: NewsResponseDtoMapper
): GetLatestNewsUseCase {

    override suspend operator fun invoke(): List<NewsData>? {
        val response = repository.getLatestNews()
        val newsResponse = mapper.mapToDomainModel(response)
        return newsResponse.data
    }
}