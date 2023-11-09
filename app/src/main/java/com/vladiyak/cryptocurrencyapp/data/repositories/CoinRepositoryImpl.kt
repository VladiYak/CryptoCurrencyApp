package com.vladiyak.cryptocurrencyapp.data.repositories


import com.vladiyak.cryptocurrencyapp.data.local.entities.FavoriteEntity
import com.vladiyak.cryptocurrencyapp.data.local.FavouriteDao
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.CoinGeckoApiService
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.CoinDetailDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.CoinItemDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.CoinMarketChartDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.TrendingCoinDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.search.SearchDto
import com.vladiyak.cryptocurrencyapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val apiService: CoinGeckoApiService,
    private val favouriteDao: FavouriteDao
) : CoinRepository {

    override suspend fun getCoins(): List<CoinItemDto> {
        return apiService.getCoinList()
    }

    override suspend fun getTrendingCoins(): List<TrendingCoinDto> {
        return apiService.getTrendingCoins().coins
    }

    override suspend fun getMarketChart(id: String, day: String): CoinMarketChartDto {
        return apiService.getMarketCharts(id, days = day)
    }

    override suspend fun getCoinDetail(id: String): CoinDetailDto {
        return apiService.getCoinDetail(id)
    }

    override suspend fun search(query: String): SearchDto {
        return apiService.search(query = query)
    }

    override fun getAllFavorite(): List<FavoriteEntity> {
        return favouriteDao.getAllFavorite()
    }

    override suspend fun deleteFavorite(favoriteEntity: FavoriteEntity) {
        favouriteDao.deleteFavorite(favoriteEntity)
    }

    override suspend fun addFavorite(favoriteEntity: FavoriteEntity) {
        favouriteDao.addFavorite(favoriteEntity)
    }


}

