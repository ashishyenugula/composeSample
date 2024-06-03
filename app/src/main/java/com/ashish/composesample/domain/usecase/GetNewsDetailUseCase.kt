package com.ashish.composesample.domain.usecase

import com.ashish.composesample.domain.model.News
import com.ashish.composesample.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsDetailUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend fun getNewsDetail(id: Int): Result<News> {
        return newsRepository.getNewsDetail(id)
    }
}

