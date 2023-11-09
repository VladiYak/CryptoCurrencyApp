package com.vladiyak.cryptocurrencyapp.presentation.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladiyak.cryptocurrencyapp.domain.models.NewsData
import com.vladiyak.cryptocurrencyapp.domain.usecases.GetLatestNewsUseCase
import com.vladiyak.cryptocurrencyapp.domain.usecases.impl.GetLatestNewsUseCaseImpl
import com.vladiyak.cryptocurrencyapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getLatestNewsUseCase: GetLatestNewsUseCase
): ViewModel() {

    private val _news = MutableLiveData<Resource<List<NewsData>>>()
    val news: LiveData<Resource<List<NewsData>>> = _news

    fun getNews() = viewModelScope.launch(Dispatchers.IO) {
        _news.postValue(Resource.Loading())

        val response = getLatestNewsUseCase()
        if (response != null) {
            _news.postValue(Resource.Success(response))
        }else {
            _news.postValue(Resource.Error("Could not retrieve news, try again!"))
        }
    }
}