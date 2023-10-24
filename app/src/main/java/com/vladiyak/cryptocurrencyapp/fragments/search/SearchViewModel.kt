package com.vladiyak.cryptocurrencyapp.fragments.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladiyak.cryptocurrencyapp.domain.repository.CoinRepository
import com.vladiyak.cryptocurrencyapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: CoinRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SearchUiState())
    val state: StateFlow<SearchUiState>
        get() = _state

    fun search(query: String) {
        viewModelScope.launch {
            repository.search(query = query).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.update { it.copy(list = result.data?.coins ?: emptyList()) }
                    }

                    is Resource.Error -> {
                        _state.update { it.copy(message = result.message ?: "Error!") }
                    }

                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }
}