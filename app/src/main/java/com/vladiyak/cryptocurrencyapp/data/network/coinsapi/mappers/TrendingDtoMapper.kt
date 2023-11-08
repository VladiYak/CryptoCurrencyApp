package com.vladiyak.cryptocurrencyapp.data.network.coinsapi.mappers

import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.TrendingCoinDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.TrendingCoinItemDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.TrendingDto
import com.vladiyak.cryptocurrencyapp.domain.models.Trending
import com.vladiyak.cryptocurrencyapp.domain.models.TrendingCoin
import com.vladiyak.cryptocurrencyapp.domain.models.TrendingCoinItem
import com.vladiyak.cryptocurrencyapp.utils.DomainMapper

class TrendingDtoMapper: DomainMapper<TrendingDto, Trending> {
    override fun mapToDomainModel(model: TrendingDto): Trending {
        return Trending(
            coins = mapCoins(model.coins)
        )
    }

    private fun mapCoin(trendingCoinDto: TrendingCoinDto): TrendingCoin {
        return TrendingCoin(
            item = mapItem(trendingCoinDto.item)
        )
    }

    fun mapCoins(trendingCoinDto: List<TrendingCoinDto>): List<TrendingCoin> {
        return trendingCoinDto.map { mapCoin(it) }
    }

    fun toDomainList(initial: List<TrendingDto>): List<Trending>{
        return initial.map { mapToDomainModel(it) }
    }

    private fun mapItem(trendingCoinItemDto: TrendingCoinItemDto): TrendingCoinItem {
        return TrendingCoinItem(
            id = trendingCoinItemDto.id,
            coinId = trendingCoinItemDto.coinId,
            name = trendingCoinItemDto.name,
            symbol = trendingCoinItemDto.symbol,
            marketCapRank = trendingCoinItemDto.marketCapRank,
            thumb = trendingCoinItemDto.thumb,
            small = trendingCoinItemDto.small,
            large = trendingCoinItemDto.large,
            slug = trendingCoinItemDto.slug,
            priceBtc = trendingCoinItemDto.priceBtc,
            score = trendingCoinItemDto.score
        )
    }

    override fun mapFromDomainModel(domainModel: Trending): TrendingDto {
        TODO("Not yet implemented")
    }
}