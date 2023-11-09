package com.vladiyak.cryptocurrencyapp.data.network.newsapi.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vladiyak.cryptocurrencyapp.domain.models.NewsResponse

@JsonClass(generateAdapter = true)
data class NewsResponseDto(
    @Json(name = "Data")
    val `data`: List<NewsDataDto>?,
    @Json(name = "Message")
    val message: String?,
    @Json(name = "Promoted")
    val promoted: List<Any>?,
    @Json(name = "Type")
    val type: Int?
)

fun NewsResponseDto.toNewsResponse(): NewsResponse {
    return NewsResponse(
        data = data?.map { it.toNewsData() },
        message = message,
        promoted = promoted,
        type = type
    )
}
