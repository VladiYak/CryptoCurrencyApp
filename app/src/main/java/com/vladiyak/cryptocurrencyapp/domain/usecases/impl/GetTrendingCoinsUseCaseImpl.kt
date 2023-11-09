package com.vladiyak.cryptocurrencyapp.domain.usecases.impl

import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.toTrendingCoin
import com.vladiyak.cryptocurrencyapp.domain.models.TrendingCoin
import com.vladiyak.cryptocurrencyapp.domain.repository.CoinRepository
import com.vladiyak.cryptocurrencyapp.domain.usecases.GetTrendingCoinsUseCase
import com.vladiyak.cryptocurrencyapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTrendingCoinsUseCaseImpl @Inject constructor(
    private val repository: CoinRepository,
): GetTrendingCoinsUseCase {

    override operator fun invoke(): Flow<Resource<List<TrendingCoin>>> = flow {
        emit(Resource.Loading())
        try {
            val coins = repository.getTrendingCoins()
            emit(Resource.Success(data = coins.map { it.toTrendingCoin() }))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "An error occurred!"))
        }
    }
}