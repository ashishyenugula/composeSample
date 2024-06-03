package com.ashish.composesample.di

import android.content.SharedPreferences
import com.ashish.composesample.data.service.NewsService
import com.ashish.composesample.util.Constants.TOKEN_KEY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl() = "https://newsapi.org/"

    @Provides
    fun provideRetrofit(
        @Named("BaseUrl") baseUrl: String,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    fun provideOkHttpClient(prefs: SharedPreferences): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor {
                val original = it.request()
                val newRequestBuilder = original.newBuilder()
                newRequestBuilder.addHeader("Content-Type", "application/json")
                newRequestBuilder.addHeader("Accept", "application/json")
                prefs.getString(TOKEN_KEY, null)?.let { token ->
                    newRequestBuilder.addHeader("Authorization", "Bearer $token")
                }
                it.proceed(newRequestBuilder.build())
            }
            .addInterceptor {
                val original = it.request()
                val newUrl = original.url
                    .newBuilder()
                    .addQueryParameter("apiKey", "5ba49288eefd467abbfb66b8a17a3269")
                    .build()
                val newRequest = original
                    .newBuilder()
                    .url(newUrl)
                    .build()

                it.proceed(newRequest)
            }
            .callTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    fun providerNewsService(retrofit: Retrofit): NewsService =
        retrofit.create(NewsService::class.java)
}