package com.vladiyak.cryptocurrencyapp.domain.usecases

import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.mappers.CoinDetailDtoMapper
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.mappers.SearchDtoMapper
import com.vladiyak.cryptocurrencyapp.domain.models.CoinDetail
import com.vladiyak.cryptocurrencyapp.domain.models.Search
import com.vladiyak.cryptocurrencyapp.domain.repository.CoinRepository
import com.vladiyak.cryptocurrencyapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: CoinRepository,
    private val searchDtoMapper: SearchDtoMapper
) {

    operator fun invoke(query: String): Flow<Resource<Search>> = flow {
        try {
            emit(Resource.Loading())
            val searchQuery = repository.search(query = query)
            emit(Resource.Success(data = searchDtoMapper.mapToDomainModel(searchQuery)))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "An error occurred!"))
        }
    }


}