package com.vladiyak.cryptocurrencyapp.domain.usecases.impl

import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.mappers.CoinMarketChartDtoMapper
import com.vladiyak.cryptocurrencyapp.domain.models.CoinMarketChart
import com.vladiyak.cryptocurrencyapp.domain.repository.CoinRepository
import com.vladiyak.cryptocurrencyapp.domain.usecases.GetMarketChartUseCase
import com.vladiyak.cryptocurrencyapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMarketChartUseCaseImpl @Inject constructor(
    private val repository: CoinRepository,
    private val marketChartDtoMapper: CoinMarketChartDtoMapper
): GetMarketChartUseCase {

    override operator fun invoke(id: String, day: String): Flow<Resource<CoinMarketChart>> = flow {
        emit(Resource.Loading())
        try {
            val marketCharts = repository.getMarketChart(id, day)
            emit(Resource.Success(data = marketChartDtoMapper.mapToDomainModel(marketCharts)))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "An error occurred!"))
        }
    }
}