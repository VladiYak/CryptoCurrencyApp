package com.vladiyak.cryptocurrencyapp.data.repositories


import com.vladiyak.cryptocurrencyapp.data.local.FavoriteEntityMapper
import com.vladiyak.cryptocurrencyapp.data.local.FavouriteDao
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.CoinGeckoApiService
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.CoinMarketChart
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.TrendingCoin
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.search.Search
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.mappers.CoinDetailDtoMapper
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.mappers.CoinItemDtoMapper
import com.vladiyak.cryptocurrencyapp.domain.models.CoinDetail
import com.vladiyak.cryptocurrencyapp.domain.models.FavoriteCoin
import com.vladiyak.cryptocurrencyapp.domain.repository.CoinRepository
import com.vladiyak.cryptocurrencyapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val apiService: CoinGeckoApiService,
    private val favouriteDao: FavouriteDao,
    private val coinDetailMapper: CoinDetailDtoMapper,
    private val coinMapper: CoinItemDtoMapper,
    private val entityMapper: FavoriteEntityMapper
) : CoinRepository {

    override fun getCoins() = flow {
        emit(Resource.Loading())
        try {
            val coins = coinMapper.toDomainList(apiService.getCoinList())
            emit(Resource.Success(data = coins))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "An error occurred!"))
        }
    }

    override fun getTrendingCoins(): Flow<Resource<List<TrendingCoin>>> = flow {
        emit(Resource.Loading())
        try {
            val coins = apiService.getTrendingCoins().coins
            emit(Resource.Success(data = coins))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "An error occurred!"))
        }
    }

    override fun getMarketChart(id: String, day: String): Flow<Resource<CoinMarketChart>> = flow {
        emit(Resource.Loading())
        try {
            val marketCharts = apiService.getMarketCharts(id, days = day)
            emit(Resource.Success(marketCharts))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "An error occurred!"))
        }
    }

    override fun getCoinDetail(id: String): Flow<Resource<CoinDetail>> = flow {
        emit(Resource.Loading())
        try {
            val coinDetail = apiService.getCoinDetail(id)
            emit(Resource.Success(data = coinDetailMapper.mapToDomainModel(coinDetail)))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "An error occurred!"))
        }
    }

    override fun search(query: String): Flow<Resource<Search>> {
        return flow {
            try {
                emit(Resource.Loading())
                val searchQuery = apiService.search(query = query)
                emit(Resource.Success(searchQuery))
            } catch (e: Exception) {
                emit(Resource.Error(message = e.localizedMessage ?: "An error occurred!"))
            }
        }
    }

    override fun getAllFavourite(): List<FavoriteCoin> {
        return entityMapper.toDomainList(favouriteDao.getAllFavourite())
    }

    override suspend fun deleteFavourite(favoriteCoin: FavoriteCoin) {
        favouriteDao.deleteFavourite(entityMapper.mapFromDomainModel(favoriteCoin))
    }

    override suspend fun addFavourite(favoriteCoin: FavoriteCoin) {
        favouriteDao.addFavourite(entityMapper.mapFromDomainModel(favoriteCoin))
    }


}

