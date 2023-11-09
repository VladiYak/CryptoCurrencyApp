package com.vladiyak.cryptocurrencyapp.domain.usecases

import com.vladiyak.cryptocurrencyapp.domain.models.CoinMarketChart
import com.vladiyak.cryptocurrencyapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetMarketChartUseCase {

    operator fun invoke(id: String, day: String): Flow<Resource<CoinMarketChart>>
}