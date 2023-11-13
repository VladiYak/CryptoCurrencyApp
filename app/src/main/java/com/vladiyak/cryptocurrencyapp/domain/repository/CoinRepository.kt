package com.vladiyak.cryptocurrencyapp.domain.repository


import com.vladiyak.cryptocurrencyapp.data.local.database.entities.FavoriteEntity
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.CoinDetailDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.CoinItemDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.CoinMarketChartDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.TrendingCoinDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.search.SearchDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinItemDto>

    suspend fun getTrendingCoins(): List<TrendingCoinDto>

    suspend fun getMarketChart(id: String, day: String): CoinMarketChartDto

    suspend fun getCoinDetail(id: String): CoinDetailDto

    suspend fun search(query: String): SearchDto

    fun getAllFavorite(): List<FavoriteEntity>

    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)

    suspend fun addFavorite(favoriteEntity: FavoriteEntity)
}
