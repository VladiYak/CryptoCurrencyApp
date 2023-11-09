package com.vladiyak.cryptocurrencyapp.domain.usecases.impl

import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.toCoinItem
import com.vladiyak.cryptocurrencyapp.domain.models.CoinItem
import com.vladiyak.cryptocurrencyapp.domain.repository.CoinRepository
import com.vladiyak.cryptocurrencyapp.domain.usecases.GetCoinsUseCase
import com.vladiyak.cryptocurrencyapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinsUseCaseImpl @Inject constructor(
    private val repository: CoinRepository
) : GetCoinsUseCase {

    override operator fun invoke(): Flow<Resource<List<CoinItem>>> = flow {
        emit(Resource.Loading())
        try {
            val coins = repository.getCoins()
            emit(Resource.Success(coins.map { it.toCoinItem() }))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "An error occurred!"))
        }
    }
}