package com.ashish.composesample.data.service

import com.ashish.composesample.data.model.response.NewsResponse
import com.ashish.composesample.util.Constants.DEFAULT_PAGE_SIZE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("sources") s:String="bbc-news",
      //  @Query("q") q: String = "bitcoin",
        @Query("page") page: Int = 1,
        @Query("size") size: Int = DEFAULT_PAGE_SIZE
    ): Response<NewsResponse>
}