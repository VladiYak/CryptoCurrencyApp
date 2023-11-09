package com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins

import com.google.gson.annotations.SerializedName
import com.vladiyak.cryptocurrencyapp.domain.models.Ath
import com.vladiyak.cryptocurrencyapp.domain.models.AthDate
import com.vladiyak.cryptocurrencyapp.domain.models.Atl
import com.vladiyak.cryptocurrencyapp.domain.models.AtlDate
import com.vladiyak.cryptocurrencyapp.domain.models.CoinDetail
import com.vladiyak.cryptocurrencyapp.domain.models.CoinImage
import com.vladiyak.cryptocurrencyapp.domain.models.CurrentPrice
import com.vladiyak.cryptocurrencyapp.domain.models.Description
import com.vladiyak.cryptocurrencyapp.domain.models.High24h
import com.vladiyak.cryptocurrencyapp.domain.models.Low24h
import com.vladiyak.cryptocurrencyapp.domain.models.MarketCap
import com.vladiyak.cryptocurrencyapp.domain.models.MarketData
import com.vladiyak.cryptocurrencyapp.domain.models.TotalVolume

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

fun CoinDetailDto.toCoinDetail(): CoinDetail {
    return CoinDetail(
        id = id,
        name = name,
        symbol = symbol,
        categories = categories,
        description = description?.toDescription(),
        image = image?.toCoinImage(),
        marketData = marketData?.toMarketData()
    )
}

fun DescriptionDto.toDescription(): Description {
    return Description(
        descriptionEN = descriptionEN
    )
}

fun CoinImageDto.toCoinImage(): CoinImage {
    return CoinImage(
        large = large
    )
}

fun MarketDataDto.toMarketData(): MarketData {
    return MarketData(
        currentPrice = currentPrice.toCurrentPrice(),
        high24h = high24h.toHigh24h(),
        low24h = low24h.toLow24h(),
        priceChangePercentage24h = priceChangePercentage24h,
        priceChangePercentage7d = priceChangePercentage7d,
        priceChangePercentage14d = priceChangePercentage14d,
        priceChangePercentage30d = priceChangePercentage30d,
        priceChangePercentage60d = priceChangePercentage60d,
        priceChangePercentage365d = priceChangePercentage365d,
        marketCap = marketCap.toMarketCap(),
        totalSupply = totalSupply,
        circulatingSupply = circulatingSupply,
        totalVolume = totalVolume.toTotalVolume(),
        ath = ath.toAth(),
        atl = atl.toAtl(),
        athDate = athDate.toAthDate(),
        atlDate = atlDate.toAtlDate(),
    )
}

fun CurrentPriceDto.toCurrentPrice(): CurrentPrice {
    return CurrentPrice(
        usd = usd
    )
}

fun High24hDto.toHigh24h(): High24h {
    return High24h(
        usd = usd
    )
}

fun Low24hDto.toLow24h(): Low24h {
    return Low24h(
        usd = usd
    )
}

fun MarketCapDto.toMarketCap(): MarketCap {
    return MarketCap(
        usd = usd
    )
}

fun TotalVolumeDto.toTotalVolume(): TotalVolume {
    return TotalVolume(
        usd = usd
    )
}

fun AthDto.toAth(): Ath {
    return Ath(
        usd = usd
    )
}

fun AtlDto.toAtl(): Atl {
    return Atl(
        usd = usd
    )
}

fun AthDateDto.toAthDate(): AthDate {
    return AthDate(
        usd = usd
    )
}

fun AtlDateDto.toAtlDate(): AtlDate {
    return AtlDate(
        usd = usd
    )
}