package com.vladiyak.cryptocurrencyapp.data.api.newapi.dto.coins

import com.google.gson.annotations.SerializedName

data class CoinDetail(
    val id: String = "",
    val name: String = "",
    val symbol: String = "",
    val categories: List<String>? = null,
    val description: Description? = null,
    val image: CoinImage? = null,
    @SerializedName("market_data")
    val marketData: MarketData? = null
)

data class Description(
    @SerializedName("en")
    val descriptionEN: String
)

data class CoinImage(
    val large: String
)

data class MarketData(
    @SerializedName("current_price")
    val currentPrice: CurrentPrice,

    @SerializedName("high_24h")
    val high24h: High24h,

    @SerializedName("low_24h")
    val low24h: Low24h,

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
    val marketCap: MarketCap,

    @SerializedName("total_supply")
    val totalSupply: Double? = 0.0,

    @SerializedName("circulating_supply")
    val circulatingSupply: Double? = 0.0,

    @SerializedName("total_volume")
    val totalVolume: TotalVolume,

    @SerializedName("ath")
    val ath: Ath,

    @SerializedName("atl")
    val atl: Atl,

    @SerializedName("ath_date")
    val athDate: AthDate,

    @SerializedName("atl_date")
    val atlDate: AthDate,



    )

data class CurrentPrice(
    val usd: Double
)

data class High24h(
    val usd: Double
)

data class Low24h(
    val usd: Double
)

data class MarketCap(
    val usd: Double
)

data class TotalVolume(
    val usd: Double
)

data class Ath(
    val usd: Double
)

data class Atl(
    val usd: Double
)

data class AthDate(
    val usd: String
)

data class AtlDate(
    val usd: String
)