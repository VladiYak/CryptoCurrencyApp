package com.vladiyak.cryptocurrencyapp.data.network.coinsapi.mappers

import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.search.CoinSearchResponseDto
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.search.SearchDto
import com.vladiyak.cryptocurrencyapp.domain.models.CoinSearchResponse
import com.vladiyak.cryptocurrencyapp.domain.models.Search
import com.vladiyak.cryptocurrencyapp.utils.DomainMapper

class SearchDtoMapper : DomainMapper<SearchDto, Search> {
    override fun mapToDomainModel(model: SearchDto): Search {
        return Search(
            coins = mapCoins(model.coins)
        )
    }

    private fun mapCoin(coinSearchResponseDto: CoinSearchResponseDto): CoinSearchResponse {
        return CoinSearchResponse(
            id = coinSearchResponseDto.id,
            name = coinSearchResponseDto.name,
            symbol = coinSearchResponseDto.symbol,
            marketCapRank = coinSearchResponseDto.marketCapRank,
            imageUrl = coinSearchResponseDto.imageUrl
        )
    }

    private fun mapCoins(coinSearchResponseDto: List<CoinSearchResponseDto>): List<CoinSearchResponse> {
        return coinSearchResponseDto.map { mapCoin(it) }
    }

    override fun mapFromDomainModel(domainModel: Search): SearchDto {
        TODO("Not yet implemented")
    }
}