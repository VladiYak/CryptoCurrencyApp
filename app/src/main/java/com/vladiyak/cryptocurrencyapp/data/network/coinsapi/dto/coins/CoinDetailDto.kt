package com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins

import com.google.gson.annotations.SerializedName

data class CoinDetailDto(
    val id: String = "",
    val name: String = "",
    val symbol: String = "",
    val categories: List<String>? = null,
    val description: DescriptionDto? = null,
    val image: CoinImageDto? = null,
    @SerializedName("market_data")
    val marketData: MarketDataDto? = null
)

data class DescriptionDto(
    @SerializedName("en")
    val descriptionEN: String
)

data class CoinImageDto(
    val large: String
)

data class MarketDataDto(
    @SerializedName("current_price")
    val currentPrice: CurrentPriceDto,

    @SerializedName("high_24h")
    val high24h: High24hDto,

    @SerializedName("low_24h")
    val low24h: Low24hDto,

    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Double = 0.0,

    @SerializedName("price_change_percentage_7d")
    val priceChangePercentage7d: Double = 0.0,

    @SerializedName("price_change_percentage_14d")
    val priceChangePercentage14d: Double = 0.0,

    @SerializedName("price_change_percentage_30d")
    val priceChangePercentage30d: Double = 0.0,

    @SerializedName("price_change_percentage_60d")
    val priceChangePercentage60d: Double = 0.0,

    @SerializedName("price_change_percentage_1y")
    val priceChangePercentage365d: Double = 0.0,

    @SerializedName("market_cap")
    val marketCap: MarketCapDto,

    @SerializedName("total_supply")
    val totalSupply: Double? = 0.0,

    @SerializedName("circulating_supply")
    val circulatingSupply: Double? = 0.0,

    @SerializedName("total_volume")
    val totalVolume: TotalVolumeDto,

    @SerializedName("ath")
    val ath: AthDto,

    @SerializedName("atl")
    val atl: AtlDto,

    @SerializedName("ath_date")
    val athDate: AthDateDto,

    @SerializedName("atl_date")
    val atlDate: AtlDateDto,



    )

data class CurrentPriceDto(
    val usd: Double
)

data class High24hDto(
    val usd: Double
)

data class Low24hDto(
    val usd: Double
)

data class MarketCapDto(
    val usd: Double
)

data class TotalVolumeDto(
    val usd: Double
)

data class AthDto(
    val usd: Double
)

data class AtlDto(
    val usd: Double
)

data class AthDateDto(
    val usd: String
)

data class AtlDateDto(
    val usd: String
)