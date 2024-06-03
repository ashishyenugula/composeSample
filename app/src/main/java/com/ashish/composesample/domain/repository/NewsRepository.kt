package com.ashish.composesample.domain.repository

import com.ashish.composesample.domain.model.News

interface NewsRepository {
    suspend fun getNewsList(): Result<List<News>>

    suspend fun getNewsDetail(id: Int): Result<News>
}