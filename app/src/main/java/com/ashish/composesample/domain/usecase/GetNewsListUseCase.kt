package com.ashish.composesample.domain.usecase

import com.ashish.composesample.domain.model.News
import com.ashish.composesample.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsListUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend fun getNewsList(): Result<List<News>> {
        return newsRepository.getNewsList()
    }
}