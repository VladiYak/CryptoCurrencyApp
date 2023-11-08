package com.vladiyak.cryptocurrencyapp.data.local.mappers

import com.vladiyak.cryptocurrencyapp.data.local.FavouriteEntity
import com.vladiyak.cryptocurrencyapp.domain.models.FavoriteCoin
import com.vladiyak.cryptocurrencyapp.utils.DomainMapper

class FavoriteEntityMapper : DomainMapper<FavouriteEntity, FavoriteCoin> {
    override fun mapToDomainModel(model: FavouriteEntity): FavoriteCoin {
        return FavoriteCoin(
            coinId = model.coinId,
            coinName = model.coinName,
            symbol = model.symbol,
            price = model.price,
            coinImage = model.coinImage,
            priceChangePercentage7d = model.priceChangePercentage7d
        )
    }

    fun toDomainList(initial: List<FavouriteEntity>): List<FavoriteCoin>{
        return initial.map { mapToDomainModel(it) }
    }

    override fun mapFromDomainModel(domainModel: FavoriteCoin): FavouriteEntity {
        return FavouriteEntity(
            coinId = domainModel.coinId,
            coinName = domainModel.coinName,
            symbol = domainModel.symbol,
            price = domainModel.price,
            coinImage = domainModel.coinImage,
            priceChangePercentage7d = domainModel.priceChangePercentage7d
        )
    }

}