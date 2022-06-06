package com.iago.newsaccessibility.repository

import com.iago.newsaccessibility.utils.Constants
import com.iago.newsaccessibility.api.NewsApi
import com.iago.newsaccessibility.models.ListNews
import com.iago.newsaccessibility.models.Resource
import retrofit2.HttpException
import javax.inject.Inject

class NewsRepository @Inject constructor(private val api: NewsApi) {

    suspend fun getNews(): Resource<ListNews> {
        val response = try {
            api.getNews(Constants.TOKEN)
        } catch (e: HttpException) {
            return Resource.Error("Couldn't request news")
        } catch (e: Exception) {
            return Resource.Error("Couldn't request news")
        }
        return Resource.Success(response)
    }

}
