package com.ashish.composesample.data.repository

import com.ashish.composesample.data.model.RequestException
import com.ashish.composesample.data.service.NewsService
import com.ashish.composesample.domain.model.News
import com.ashish.composesample.domain.repository.NewsRepository
import java.net.HttpURLConnection
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService
) : NewsRepository {

    private val cachedList: MutableList<News> = mutableListOf()

    override suspend fun getNewsList(): Result<List<News>> {
        val apiResponse = newsService.getNews().body()
        if (apiResponse?.status == "ok") {
            val newsList = apiResponse.articles ?: emptyList()
            cachedList.addAll(newsList)
            return Result.success(newsList)
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!"
            )
        )
    }

    override suspend fun getNewsDetail(id: Int): Result<News> {
        return cachedList.find { it.id == id }?.let { news ->
            Result.success(news)
        } ?: run {
            Result.failure(Exception("An error occurred when get new detail"))
        }
    }
}