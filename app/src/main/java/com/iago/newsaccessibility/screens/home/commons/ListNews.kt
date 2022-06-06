package com.iago.newsaccessibility.screens.home.commons

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iago.newsaccessibility.models.Article

@Composable
fun ListNews(news: List<Article>) {

    val newsOpened = remember { mutableStateOf<Int?>(null) }

    BackHandler(true) {
        if (newsOpened.value != null)
            newsOpened.value = null
    }

    if (newsOpened.value != null)
        NewsPage(news[newsOpened.value!!])
    else
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
                .padding(10.dp)
        ) {
            items(news.size) { index ->
                NewsItem(news[index], index) { newsOpened.value = it }
                Spacer(modifier = Modifier.height(7.dp))
            }
        }
}