package com.vladiyak.cryptocurrencyapp.data.network.coinsapi.mappers

import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.AthDateDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.AthDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.AtlDateDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.AtlDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.CoinDetailDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.CoinImageDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.CoinItemDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.CurrentPriceDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.DescriptionDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.High24hDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.Low24hDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.MarketCapDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.MarketDataDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.SparklineIn7dDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.TotalVolumeDto
import com.vladiyak.cryptocurrencyapp.domain.models.Ath
import com.vladiyak.cryptocurrencyapp.domain.models.AthDate
import com.vladiyak.cryptocurrencyapp.domain.models.Atl
import com.vladiyak.cryptocurrencyapp.domain.models.AtlDate
import com.vladiyak.cryptocurrencyapp.domain.models.CoinDetail
import com.vladiyak.cryptocurrencyapp.domain.models.CoinImage
import com.vladiyak.cryptocurrencyapp.domain.models.CoinItem
import com.vladiyak.cryptocurrencyapp.domain.models.CurrentPrice
import com.vladiyak.cryptocurrencyapp.domain.models.Description
import com.vladiyak.cryptocurrencyapp.domain.models.High24h
import com.vladiyak.cryptocurrencyapp.domain.models.Low24h
import com.vladiyak.cryptocurrencyapp.domain.models.MarketCap
import com.vladiyak.cryptocurrencyapp.domain.models.MarketData
import com.vladiyak.cryptocurrencyapp.domain.models.SparklineIn7d
import com.vladiyak.cryptocurrencyapp.domain.models.TotalVolume
import com.vladiyak.cryptocurrencyapp.domain.util.DomainMapper

class CoinItemDtoMapper : DomainMapper<CoinItemDto, CoinItem> {

    override fun mapToDomainModel(model: CoinItemDto): CoinItem {
        return CoinItem(
            id = model.id,
            name = model.name,
            symbol = model.symbol,
            image = model.image,
            currentPrice = model.currentPrice,
            marketCap = model.marketCap,
            marketCapRank = model.marketCapRank,
            fullyDilutedValuation = model.fullyDilutedValuation,
            totalVolume = model.totalVolume,
            high24h = model.high24h,
            low24h = model.low24h,
            priceChange24h = model.priceChange24h,
            priceChangePercentage24h = model.priceChangePercentage24h,
            priceChangePercentage7dInCurrency = model.priceChangePercentage7dInCurrency,
            marketCapChange24h = model.marketCapChange24h,
            marketCapChangePercentage24h = model.marketCapChangePercentage24h,
            circulatingSupply = model.circulatingSupply,
            totalSupply = model.totalSupply,
            ath = model.ath,
            athChangePercentage = model.athChangePercentage,
            athDate = model.athDate,
            atl = model.atl,
            atlChangePercentage = model.atlChangePercentage,
            atlDate = model.atlDate,
            lastUpdated = model.lastUpdated,
            priceChangePercentage1hInCurrency = model.priceChangePercentage1hInCurrency,
            sparklineIn7d = mapSparkline(model.sparklineIn7d)

            )
    }

    private fun mapSparkline(sparklineIn7dDto: SparklineIn7dDto?): SparklineIn7d {
        return SparklineIn7d(
            price = sparklineIn7dDto?.price
        )
    }

    fun toDomainList(initial: List<CoinItemDto>): List<CoinItem>{
        return initial.map { mapToDomainModel(it) }
    }

    override fun mapFromDomainModel(domainModel: CoinItem): CoinItemDto {
        TODO("Not yet implemented")
    }
}
