package com.vladiyak.cryptocurrencyapp.data.network.coinsapi

import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.CoinDetailDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.CoinItemDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.CoinMarketChartDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.TrendingDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.search.SearchDto
import com.vladiyak.cryptocurrencyapp.utils.ConstantsNew
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinGeckoApiService {

    @GET(ConstantsNew.COIN_LIST_URL)
    suspend fun getCoinList(
        @Query("vs_currency") currency: String = "usd",
        @Query("page") page: Int = 1,
        @Query("per_page") numCoinsPerPage: Int = 100,
        @Query("order") order: String = "market_cap_desc",
        @Query("sparkline") includeSparkline7dData: Boolean = true,
        @Query("price_change_percentage") priceChangePercentageIntervals: String = "7d",
    ): List<CoinItemDto>

    @GET(ConstantsNew.TRENDING_URL)
    suspend fun getTrendingCoins(): TrendingDto


    @GET(ConstantsNew.MARKET_CHARTS)
    suspend fun getMarketCharts(
        @Path("id") id: String,
        @Query("vs_currency") vsCurrency: String = "usd",
        @Query("days") days: String = "7",
    ): CoinMarketChartDto

    @GET(ConstantsNew.COIN_DETAIL)
    suspend fun getCoinDetail(
        @Path("id") id: String
    ): CoinDetailDto

    @GET(ConstantsNew.SEARCH)
    suspend fun search(
        @Query("query") query: String
    ): SearchDto

}