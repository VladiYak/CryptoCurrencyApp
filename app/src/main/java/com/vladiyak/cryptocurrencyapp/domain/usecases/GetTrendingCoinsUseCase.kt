package com.vladiyak.cryptocurrencyapp.domain.usecases

import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.mappers.CoinItemDtoMapper
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.mappers.TrendingDtoMapper
import com.vladiyak.cryptocurrencyapp.domain.models.CoinItem
import com.vladiyak.cryptocurrencyapp.domain.models.TrendingCoin
import com.vladiyak.cryptocurrencyapp.domain.repository.CoinRepository
import com.vladiyak.cryptocurrencyapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTrendingCoinsUseCase @Inject constructor(
    private val repository: CoinRepository,
    private val trendingDtoMapper: TrendingDtoMapper
) {

    operator fun invoke(): Flow<Resource<List<TrendingCoin>>> = flow {
        emit(Resource.Loading())
        try {
            val coins = repository.getTrendingCoins()
            emit(Resource.Success(data = trendingDtoMapper.mapCoins(coins)))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "An error occurred!"))
        }
    }
}