package com.vladiyak.cryptocurrencyapp.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladiyak.cryptocurrencyapp.domain.repository.CoinRepository
import com.vladiyak.cryptocurrencyapp.domain.model.FavouriteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val coinRepository: CoinRepository
): ViewModel() {

    private var _allFavouriteCoin: MutableLiveData<List<FavouriteEntity>> =
        MutableLiveData<List<FavouriteEntity>>()
    val allFavouriteCoin: LiveData<List<FavouriteEntity>> = _allFavouriteCoin


    fun addToFavourites(favouriteEntity: FavouriteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            coinRepository.addFavourite(favouriteEntity)
        }
    }

    fun getAllFavouriteCoin() {
        viewModelScope.launch(Dispatchers.IO) {
            _allFavouriteCoin.postValue(coinRepository.getAllFavourite())
        }
    }

    fun removeCoinFromFavourite(entity: FavouriteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            coinRepository.delFavourite(entity)
        }
    }
    init {
        getAllFavouriteCoin()
    }
}