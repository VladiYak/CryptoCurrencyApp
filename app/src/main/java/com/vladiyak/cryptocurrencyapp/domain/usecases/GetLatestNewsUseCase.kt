package com.vladiyak.cryptocurrencyapp.domain.usecases

import com.vladiyak.cryptocurrencyapp.domain.models.NewsData

interface GetLatestNewsUseCase {

    suspend operator fun invoke(): List<NewsData>?
}