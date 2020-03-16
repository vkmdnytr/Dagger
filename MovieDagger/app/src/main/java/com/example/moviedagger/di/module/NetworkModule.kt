package com.example.moviedagger.di.module

import com.example.moviedagger.model.rest.ApiClient
import com.example.moviedagger.model.rest.MovieApi
import com.example.moviedagger.model.rest.ServiceUrls
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {


    @Provides
    @Singleton
    fun provideHttpClient() : OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    }

    @Provides
    @Singleton
    internal fun provideRetrofit(httpClient : OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(ServiceUrls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(httpClient)
                .build()

    }

    @Provides
    @Singleton
    internal fun provideGithubApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    internal fun provideGithubApiClient(githubApi: MovieApi): ApiClient {
        return ApiClient(githubApi)
    }


}