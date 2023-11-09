package com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.search

import com.vladiyak.cryptocurrencyapp.domain.models.Search

data class SearchDto(
    val coins: List<CoinSearchResponseDto>
)

fun SearchDto.toSearch(): Search {
    return Search(
        coins = coins.map { it.toCoinSearchResponse() }
    )
}
