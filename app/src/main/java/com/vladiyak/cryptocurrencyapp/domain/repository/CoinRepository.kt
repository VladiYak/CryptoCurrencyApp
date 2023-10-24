package com.vladiyak.cryptocurrencyapp.domain.repository


import com.vladiyak.cryptocurrencyapp.api.newapi.dto.coins.CoinDetail
import com.vladiyak.cryptocurrencyapp.api.newapi.dto.coins.CoinItem
import com.vladiyak.cryptocurrencyapp.api.newapi.dto.coins.CoinMarketChart
import com.vladiyak.cryptocurrencyapp.api.newapi.dto.coins.TrendingCoin
import com.vladiyak.cryptocurrencyapp.api.newapi.dto.detail.CoinChartResponse
import com.vladiyak.cryptocurrencyapp.api.newapi.dto.markets.Exchange
import com.vladiyak.cryptocurrencyapp.api.newapi.dto.search.Search
import com.vladiyak.cryptocurrencyapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CoinRepository {

    fun getMarketList(): Flow<Resource<List<Exchange>>>
    fun getCoins(): Flow<Resource<List<CoinItem>>>
    fun getTrendingCoins(): Flow<Resource<List<TrendingCoin>>>

    fun getMarketChart(id: String, day: Int): Flow<Resource<CoinMarketChart>>

    fun getCoinDetail(id: String): Flow<Resource<CoinDetail>>

    fun search(query: String): Flow<Resource<Search>>

    suspend fun getCoinChartData(id: String, period: String): Response<CoinChartResponse>
}
