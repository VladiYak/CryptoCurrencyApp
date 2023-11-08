package com.vladiyak.cryptocurrencyapp.data.network.newsapi.mappers

import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.DescriptionDto
import com.vladiyak.cryptocurrencyapp.data.network.newsapi.dto.NewsDataDto
import com.vladiyak.cryptocurrencyapp.data.network.newsapi.dto.NewsResponseDto
import com.vladiyak.cryptocurrencyapp.data.network.newsapi.dto.SourceInfoDto
import com.vladiyak.cryptocurrencyapp.domain.models.Description
import com.vladiyak.cryptocurrencyapp.domain.models.NewsData
import com.vladiyak.cryptocurrencyapp.domain.models.NewsResponse
import com.vladiyak.cryptocurrencyapp.domain.models.SourceInfo
import com.vladiyak.cryptocurrencyapp.utils.DomainMapper

class NewsResponseDtoMapper: DomainMapper<NewsResponseDto?, NewsResponse> {
    override fun mapToDomainModel(model: NewsResponseDto?): NewsResponse {
        return NewsResponse(
            data = mapNewsDataList(model?.data),
            message = model?.message,
            promoted = model?.promoted,
            type = model?.type
        )
    }

    private fun mapNewsData(newsDataDto: NewsDataDto): NewsData {
        return NewsData(
            body = newsDataDto.body,
            categories = newsDataDto.categories,
            downvotes = newsDataDto.downvotes,
            guid = newsDataDto.guid,
            id = newsDataDto.id,
            imageurl = newsDataDto.imageurl,
            lang = newsDataDto.lang,
            publishedOn = newsDataDto.publishedOn,
            source = newsDataDto.source,
            sourceInfo = mapSourceInfo(newsDataDto.sourceInfo),
            tags = newsDataDto.tags,
            title = newsDataDto.title,
            upvotes = newsDataDto.upvotes,
            url = newsDataDto.url
        )
    }

    private fun mapSourceInfo(sourceInfoDto: SourceInfoDto?): SourceInfo {
        return SourceInfo(
            img = sourceInfoDto?.img,
            lang = sourceInfoDto?.lang,
            name = sourceInfoDto?.name
        )
    }

    private fun mapNewsDataList(newsDataDtoList: List<NewsDataDto>?): List<NewsData>? {
        return newsDataDtoList?.map { mapNewsData(it) }
    }

    override fun mapFromDomainModel(domainModel: NewsResponse): NewsResponseDto {
        TODO("Not yet implemented")
    }

}