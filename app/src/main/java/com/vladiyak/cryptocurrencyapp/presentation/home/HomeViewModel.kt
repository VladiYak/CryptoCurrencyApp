package com.vladiyak.cryptocurrencyapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladiyak.cryptocurrencyapp.domain.repository.SettingsRepository
import com.vladiyak.cryptocurrencyapp.domain.usecases.GetCoinsUseCase
import com.vladiyak.cryptocurrencyapp.domain.usecases.GetTrendingCoinsUseCase
import com.vladiyak.cryptocurrencyapp.domain.usecases.impl.GetTrendingCoinsUseCaseImpl
import com.vladiyak.cryptocurrencyapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val getTrendingCoinsUseCase: GetTrendingCoinsUseCase,
    private val repository: SettingsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListUiState())
    val state: StateFlow<CoinListUiState>
        get() = _state

    init {
        getCoins()
        getTrendingCoins()
    }

    fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update { it.copy(coinList = result.data ?: emptyList()) }
                    _state.update { it.copy(isLoading = false) }

                }

                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }

                is Resource.Error -> {
                    _state.update { it.copy(message = result.message ?: "Error!") }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getTrendingCoins() {
        getTrendingCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update { it.copy(trendingCoinList = result.data ?: emptyList()) }
                    _state.update { it.copy(isLoading = false) }
                }

                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }

                is Resource.Error -> {
                    _state.update { it.copy(message = result.message ?: "Error!") }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun isDarkModeOn(): Boolean {
        return repository.isDarkModeEnabled()
    }
}