package com.iago.newsaccessibility.modules

import com.iago.newsaccessibility.utils.Constants
import com.iago.newsaccessibility.api.NewsApi
import com.iago.newsaccessibility.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideNewsRepository(api: NewsApi) = NewsRepository(api)

    @Singleton
    @Provides
    fun provideApi(): NewsApi = Retrofit.Builder()
        .addConverterFactory((GsonConverterFactory.create()))
        .baseUrl(Constants.API_BASE_URL)
        .build()
        .create(NewsApi::class.java)

}