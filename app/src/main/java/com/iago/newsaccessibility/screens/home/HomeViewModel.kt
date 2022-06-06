package com.iago.newsaccessibility.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iago.newsaccessibility.models.Article
import com.iago.newsaccessibility.models.Resource
import com.iago.newsaccessibility.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: NewsRepository,
) : ViewModel() {

    var loading = mutableStateOf(false)
    var error = mutableStateOf<String?>("")

    private val _news = MutableStateFlow<List<Article>>(emptyList())
    val news = _news.asStateFlow()

    fun getNews() {
        viewModelScope.launch {
            loading.value = true
            error.value = ""
            when (val response = repository.getNews()) {
                is Resource.Success -> _news.value = response.data!!.articles
                is Resource.Error -> error.value = response.message
            }
            loading.value = false
        }
    }
}