package com.vladiyak.cryptocurrencyapp.data.network.coinsapi.mappers

import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.AthDateDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.AthDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.AtlDateDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.AtlDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.CoinDetailDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.CoinImageDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.CurrentPriceDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.DescriptionDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.High24hDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.Low24hDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.MarketCapDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.MarketDataDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.TotalVolumeDto
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
import com.vladiyak.cryptocurrencyapp.domain.util.DomainMapper

class CoinDetailDtoMapper : DomainMapper<CoinDetailDto, CoinDetail> {

    override fun mapToDomainModel(model: CoinDetailDto): CoinDetail {
        return CoinDetail(
            id = model.id,
            name = model.name,
            symbol = model.symbol,
            categories = model.categories,
            description = mapDescription(model.description),
            image = mapCoinImage(model.image),
            marketData = mapMarketData(model.marketData)
            )
    }

    fun toDomainList(initial: List<CoinDetailDto>): List<CoinDetail>{
        return initial.map { mapToDomainModel(it) }
    }

    private fun mapDescription(descriptionDto: DescriptionDto?): Description {
        return Description(
            descriptionEN = descriptionDto?.descriptionEN ?: ""
        )
    }

    private fun mapCoinImage(coinImageDto: CoinImageDto?): CoinImage {
        return CoinImage(
            large = coinImageDto?.large ?: ""
        )
    }

    private fun mapMarketData(marketDataDto: MarketDataDto?): MarketData {
        return MarketData(
            currentPrice = mapCurrentPrice(marketDataDto?.currentPrice) ,
            high24h = mapHigh24h(marketDataDto?.high24h),
            low24h = mapLow24h(marketDataDto?.low24h),
            priceChangePercentage24h = marketDataDto?.priceChangePercentage24h ?: 0.0,
            priceChangePercentage7d = marketDataDto?.priceChangePercentage7d ?: 0.0,
            priceChangePercentage14d = marketDataDto?.priceChangePercentage14d ?: 0.0,
            priceChangePercentage30d = marketDataDto?.priceChangePercentage30d ?: 0.0,
            priceChangePercentage60d = marketDataDto?.priceChangePercentage60d ?: 0.0,
            priceChangePercentage365d = marketDataDto?.priceChangePercentage365d ?: 0.0,
            marketCap = mapMarketCap(marketDataDto?.marketCap),
            totalSupply = marketDataDto?.totalSupply,
            circulatingSupply = marketDataDto?.circulatingSupply,
            totalVolume = mapTotalVolume(marketDataDto?.totalVolume),
            ath = mapAth(marketDataDto?.ath),
            atl = mapAtl(marketDataDto?.atl),
            athDate = mapAthDate(marketDataDto?.athDate),
            atlDate = mapAtlDate(marketDataDto?.atlDate),
        )
    }

    private fun mapCurrentPrice(currentPriceDto: CurrentPriceDto?): CurrentPrice {
        return CurrentPrice(
            usd = currentPriceDto?.usd ?: 0.0
        )
    }

    private fun mapHigh24h(high24hDto: High24hDto?): High24h {
        return High24h(
            usd = high24hDto?.usd ?: 0.0
        )
    }

    private fun mapLow24h(low24hDto: Low24hDto?): Low24h {
        return Low24h(
            usd = low24hDto?.usd ?: 0.0
        )
    }

    private fun mapMarketCap(marketCapDto: MarketCapDto?): MarketCap {
        return MarketCap(
            usd = marketCapDto?.usd ?: 0.0
        )
    }

    private fun mapTotalVolume(totalVolumeDto: TotalVolumeDto?): TotalVolume {
        return TotalVolume(
            usd = totalVolumeDto?.usd ?: 0.0
        )
    }

    private fun mapAth(athDto: AthDto?): Ath {
        return Ath(
            usd = athDto?.usd ?: 0.0
        )
    }

    private fun mapAtl(atlDto: AtlDto?): Atl {
        return Atl(
            usd = atlDto?.usd ?: 0.0
        )
    }

    private fun mapAthDate(athDateDto: AthDateDto?): AthDate {
        return AthDate(
            usd = athDateDto?.usd ?: ""
        )
    }

    private fun mapAtlDate(atlDateDto: AtlDateDto?): AtlDate {
        return AtlDate(
            usd = atlDateDto?.usd ?: ""
        )
    }

    override fun mapFromDomainModel(domainModel: CoinDetail): CoinDetailDto {
        return CoinDetailDto()
    }
}
