package com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins

import com.google.gson.annotations.SerializedName
import com.vladiyak.cryptocurrencyapp.domain.models.TrendingCoinItem

data class TrendingCoinItemDto(
    val id: String,
    @SerializedName("coin_id")
    val coinId: Int,
    val name: String,
    val symbol: String,
    @SerializedName("market_cap_rank")
    val marketCapRank: Int,
    val thumb: String,
    val small: String,
    val large: String,
    val slug: String,
    @SerializedName("price_btc")
    val priceBtc: Double,
    val score: Int
)

fun TrendingCoinItemDto.toTrendingCoinItem(): TrendingCoinItem {
    return TrendingCoinItem(
        id = id,
        coinId = coinId,
        name = name,
        symbol = symbol,
        marketCapRank = marketCapRank,
        thumb = thumb,
        small = small,
        large = large,
        slug = slug,
        priceBtc = priceBtc,
        score = score
    )
}