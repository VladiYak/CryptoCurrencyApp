package com.vladiyak.cryptocurrencyapp.presentation.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladiyak.cryptocurrencyapp.domain.models.NewsData
import com.vladiyak.cryptocurrencyapp.domain.repository.CryptoCompareRepository
import com.vladiyak.cryptocurrencyapp.utils.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: CryptoCompareRepository
): ViewModel() {

    private val _news = MutableLiveData<ApiResponse<List<NewsData>>>()
    val news: LiveData<ApiResponse<List<NewsData>>> = _news

    fun getNews() = viewModelScope.launch(Dispatchers.IO) {
        _news.postValue(ApiResponse.Loading())

        val response = repository.getLatestNews()
        if (response != null) {
            _news.postValue(ApiResponse.Success(response))
        }else {
            _news.postValue(ApiResponse.Error("Could not retrieve news, try again!"))
        }
    }
}