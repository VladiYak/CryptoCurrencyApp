package com.vladiyak.cryptocurrencyapp.data.network.coinsapi.mappers

import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.CoinMarketChartDto
import com.vladiyak.cryptocurrencyapp.domain.models.CoinMarketChart
import com.vladiyak.cryptocurrencyapp.utils.DomainMapper

class CoinMarketChartDtoMapper: DomainMapper<CoinMarketChartDto, CoinMarketChart> {
    override fun mapToDomainModel(model: CoinMarketChartDto): CoinMarketChart {
        return CoinMarketChart(
            prices = model.prices,
            marketCaps = model.marketCaps,
            totalVolumes = model.totalVolumes
        )
    }

    fun toDomainList(initial: List<CoinMarketChartDto>): List<CoinMarketChart>{
        return initial.map { mapToDomainModel(it) }
    }

    override fun mapFromDomainModel(domainModel: CoinMarketChart): CoinMarketChartDto {
        TODO("Not yet implemented")
    }
}