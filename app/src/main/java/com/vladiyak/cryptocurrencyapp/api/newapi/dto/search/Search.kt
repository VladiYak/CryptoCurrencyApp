package com.vladiyak.cryptocurrencyapp.api.newapi.dto.search

import com.vladiyak.cryptocurrencyapp.api.newapi.dto.search.CoinSearchResponse

data class Search(
    val coins: List<CoinSearchResponse>,
    //val exchanges: List<ExchangeSearchResponse>
)

//data class ExchangeSearchResponse(
//    val id: String,
//    val name: String,
//    @SerializedName("large")
//    val imageUrl: String
//)