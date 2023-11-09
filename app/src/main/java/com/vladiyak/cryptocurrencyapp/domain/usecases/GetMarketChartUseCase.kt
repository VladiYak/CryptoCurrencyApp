package com.vladiyak.cryptocurrencyapp.domain.usecases

import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.mappers.CoinItemDtoMapper
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.mappers.CoinMarketChartDtoMapper
import com.vladiyak.cryptocurrencyapp.domain.models.CoinItem
import com.vladiyak.cryptocurrencyapp.domain.models.CoinMarketChart
import com.vladiyak.cryptocurrencyapp.domain.repository.CoinRepository
import com.vladiyak.cryptocurrencyapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMarketChartUseCase @Inject constructor(
    private val repository: CoinRepository,
    private val marketChartDtoMapper: CoinMarketChartDtoMapper
) {

    operator fun invoke(id: String, day: String): Flow<Resource<CoinMarketChart>> = flow {
        emit(Resource.Loading())
        try {
            val marketCharts = repository.getMarketChart(id, day)
            emit(Resource.Success(data = marketChartDtoMapper.mapToDomainModel(marketCharts)))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "An error occurred!"))
        }
    }
}