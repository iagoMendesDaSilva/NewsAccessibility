package com.iago.newsaccessibility.api

import com.iago.newsaccessibility.models.ListNews
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface NewsApi {

    @GET("top-headlines?country=us")
    suspend fun getNews(@Header("Authorization") token: String): ListNews


}