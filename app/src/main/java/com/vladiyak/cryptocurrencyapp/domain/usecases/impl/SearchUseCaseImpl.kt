package com.vladiyak.cryptocurrencyapp.domain.usecases.impl

import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.search.toSearch
import com.vladiyak.cryptocurrencyapp.domain.models.Search
import com.vladiyak.cryptocurrencyapp.domain.repository.CoinRepository
import com.vladiyak.cryptocurrencyapp.domain.usecases.SearchUseCase
import com.vladiyak.cryptocurrencyapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchUseCaseImpl @Inject constructor(
    private val repository: CoinRepository
): SearchUseCase {

    override operator fun invoke(query: String): Flow<Resource<Search>> = flow {
        try {
            emit(Resource.Loading())
            val searchQuery = repository.search(query = query)
            emit(Resource.Success(data = searchQuery.toSearch()))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "An error occurred!"))
        }
    }


}