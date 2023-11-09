package com.vladiyak.cryptocurrencyapp.domain.usecases

import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.mappers.CoinDetailDtoMapper
import com.vladiyak.cryptocurrencyapp.domain.models.CoinDetail
import com.vladiyak.cryptocurrencyapp.domain.models.CoinItem
import com.vladiyak.cryptocurrencyapp.domain.repository.CoinRepository
import com.vladiyak.cryptocurrencyapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinDetailsUseCase @Inject constructor(
    private val repository: CoinRepository,
    private val coinDetailDtoMapper: CoinDetailDtoMapper
) {

    operator fun invoke(id: String): Flow<Resource<CoinDetail>> = flow {
        emit(Resource.Loading())
        try {
            val coinDetail = repository.getCoinDetail(id)
            emit(Resource.Success(data = coinDetailDtoMapper.mapToDomainModel(coinDetail)))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "An error occurred!"))
        }
    }


}