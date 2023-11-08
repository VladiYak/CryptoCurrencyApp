package com.vladiyak.cryptocurrencyapp.domain.models

import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.search.CoinSearchResponseDto

data class Search(
    val coins: List<CoinSearchResponse>
)
