package com.iago.newsaccessibility.models

data class ListNews(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)