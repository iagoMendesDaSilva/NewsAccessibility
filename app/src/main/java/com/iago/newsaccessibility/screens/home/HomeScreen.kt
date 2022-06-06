package com.iago.newsaccessibility.screens.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.iago.newsaccessibility.screens.home.commons.ListNews
import com.iago.newsaccessibility.screens.home.commons.Load
import com.iago.newsaccessibility.ui.theme.newsaccessibilityTheme

@Composable
fun NewsScreen() {

    val context = LocalContext.current
    val homeViewModel = hiltViewModel<HomeViewModel>()

    val news = homeViewModel.news.collectAsState().value

    LaunchedEffect(key1 = true, block = {
        homeViewModel.getNews()
    })

    LaunchedEffect(key1 = homeViewModel.error.value, block = {
        if (!homeViewModel.error.value.isNullOrEmpty())
            Toast.makeText(context, homeViewModel.error.value, Toast.LENGTH_SHORT)
                .show()
    })

    newsaccessibilityTheme {
        Box(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colors.background), contentAlignment = Alignment.Center) {
            if (homeViewModel.loading.value)
                Load()
            else
                ListNews(news)
        }
    }
}




