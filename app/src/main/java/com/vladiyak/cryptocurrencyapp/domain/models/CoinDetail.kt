package com.vladiyak.cryptocurrencyapp.domain.models



data class CoinDetail(
    val id: String = "",
    val name: String = "",
    val symbol: String = "",
    val categories: List<String>? = null,
    val description: Description? = null,
    val image: CoinImage? = null,
    val marketData: MarketData? = null
)

data class Description(
    val descriptionEN: String
)

data class CoinImage(
    val large: String
)

data class MarketData(
    val currentPrice: CurrentPrice,
    val high24h: High24h,
    val low24h: Low24h,
    val priceChangePercentage24h: Double = 0.0,
    val priceChangePercentage7d: Double = 0.0,
    val priceChangePercentage14d: Double = 0.0,
    val priceChangePercentage30d: Double = 0.0,
    val priceChangePercentage60d: Double = 0.0,
    val priceChangePercentage365d: Double = 0.0,
    val marketCap: MarketCap,
    val totalSupply: Double? = 0.0,
    val circulatingSupply: Double? = 0.0,
    val totalVolume: TotalVolume,
    val ath: Ath,
    val atl: Atl,
    val athDate: AthDate,
    val atlDate: AtlDate
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