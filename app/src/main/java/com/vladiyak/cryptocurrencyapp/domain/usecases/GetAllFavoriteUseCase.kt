package com.vladiyak.cryptocurrencyapp.domain.usecases

import com.vladiyak.cryptocurrencyapp.data.local.mappers.FavoriteEntityMapper
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.mappers.CoinDetailDtoMapper
import com.vladiyak.cryptocurrencyapp.domain.models.FavoriteCoin
import com.vladiyak.cryptocurrencyapp.domain.repository.CoinRepository
import javax.inject.Inject

class GetAllFavoriteUseCase @Inject constructor(
    private val repository: CoinRepository,
    private val favoriteEntityMapper: FavoriteEntityMapper
) {

    operator fun invoke(): List<FavoriteCoin> {
        return favoriteEntityMapper.toDomainList(repository.getAllFavorite())
    }
}