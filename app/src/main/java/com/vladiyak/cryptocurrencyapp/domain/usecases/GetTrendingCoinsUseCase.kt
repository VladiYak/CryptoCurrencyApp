package com.vladiyak.cryptocurrencyapp.domain.usecases

import com.vladiyak.cryptocurrencyapp.domain.models.TrendingCoin
import com.vladiyak.cryptocurrencyapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetTrendingCoinsUseCase {

    operator fun invoke(): Flow<Resource<List<TrendingCoin>>>
}