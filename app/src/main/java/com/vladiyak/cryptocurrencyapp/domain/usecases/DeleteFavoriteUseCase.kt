package com.vladiyak.cryptocurrencyapp.domain.usecases

import com.vladiyak.cryptocurrencyapp.data.local.FavoriteEntity
import com.vladiyak.cryptocurrencyapp.data.local.mappers.FavoriteEntityMapper
import com.vladiyak.cryptocurrencyapp.domain.models.FavoriteCoin
import com.vladiyak.cryptocurrencyapp.domain.repository.CoinRepository
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val repository: CoinRepository,
    private val favoriteEntityMapper: FavoriteEntityMapper
) {

    suspend operator fun invoke(favoriteCoin: FavoriteCoin) {
        repository.deleteFavorite(favoriteEntityMapper.mapFromDomainModel(favoriteCoin))
    }
}