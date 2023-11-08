package com.vladiyak.cryptocurrencyapp.domain.repository


import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.search.SearchDto
import com.vladiyak.cryptocurrencyapp.domain.models.CoinDetail
import com.vladiyak.cryptocurrencyapp.domain.models.CoinItem
import com.vladiyak.cryptocurrencyapp.domain.models.CoinMarketChart
import com.vladiyak.cryptocurrencyapp.domain.models.FavoriteCoin
import com.vladiyak.cryptocurrencyapp.domain.models.Search
import com.vladiyak.cryptocurrencyapp.domain.models.TrendingCoin
import com.vladiyak.cryptocurrencyapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun getCoins(): Flow<Resource<List<CoinItem>>>

    fun getTrendingCoins(): Flow<Resource<List<TrendingCoin>>>

    fun getMarketChart(id: String, day: String): Flow<Resource<CoinMarketChart>>

    fun getCoinDetail(id: String): Flow<Resource<CoinDetail>>

    fun search(query: String): Flow<Resource<Search>>

    fun getAllFavourite(): List<FavoriteCoin>

    suspend fun deleteFavourite(favoriteCoin: FavoriteCoin)

    suspend fun addFavourite(favoriteCoin: FavoriteCoin)
}
