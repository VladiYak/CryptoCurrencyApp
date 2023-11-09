package com.vladiyak.cryptocurrencyapp.domain.usecases.impl

import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.toCoinDetail
import com.vladiyak.cryptocurrencyapp.domain.models.CoinDetail
import com.vladiyak.cryptocurrencyapp.domain.repository.CoinRepository
import com.vladiyak.cryptocurrencyapp.domain.usecases.GetCoinDetailsUseCase
import com.vladiyak.cryptocurrencyapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinDetailsUseCaseImpl @Inject constructor(
    private val repository: CoinRepository
): GetCoinDetailsUseCase {

    override operator fun invoke(id: String): Flow<Resource<CoinDetail>> = flow {
        emit(Resource.Loading())
        try {
            val coinDetail = repository.getCoinDetail(id)
            emit(Resource.Success(data = coinDetail.toCoinDetail()))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "An error occurred!"))
        }
    }


}