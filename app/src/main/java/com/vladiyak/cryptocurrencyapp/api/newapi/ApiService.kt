package com.vladiyak.cryptocurrencyapp.api.newapi

import com.vladiyak.cryptocurrencyapp.api.newapi.dto.coins.CoinDetail
import com.vladiyak.cryptocurrencyapp.api.newapi.dto.coins.CoinItem
import com.vladiyak.cryptocurrencyapp.api.newapi.dto.coins.CoinMarketChart
import com.vladiyak.cryptocurrencyapp.api.newapi.dto.coins.Trending
import com.vladiyak.cryptocurrencyapp.api.newapi.dto.detail.CoinChartResponse
import com.vladiyak.cryptocurrencyapp.api.newapi.dto.markets.Exchange
import com.vladiyak.cryptocurrencyapp.api.newapi.dto.search.Search
import com.vladiyak.cryptocurrencyapp.utils.ConstantsNew
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(ConstantsNew.COIN_LIST_URL)
    suspend fun getCoinList(): List<CoinItem>

    @GET(ConstantsNew.TRENDING_URL)
    suspend fun getTrendingCoins(): Trending

    @GET(ConstantsNew.MARKETS_URL)
    suspend fun getMarketList(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 50
    ): List<Exchange>

    @GET(ConstantsNew.MARKET_CHARTS)
    suspend fun getMarketCharts(
        @Path("id") id: String,
        @Query("vs_currency") vsCurrency: String = "usd",
        @Query("days") days: String = "7",
    ): CoinMarketChart

    @GET(ConstantsNew.COIN_DETAIL)
    suspend fun getCoinDetail(
        @Path("id") id: String
    ): CoinDetail

    @GET(ConstantsNew.SEARCH)
    suspend fun search(
        @Query("query") query: String
    ): Search

    @GET("coins/{id}/market_chart")
    suspend fun getCoinChartData(
        @Path("id") id: String,
        @Query("vs_currency") currency: String,
        @Query("days") period: String
    ): Response<CoinChartResponse>
}