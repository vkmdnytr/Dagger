package com.example.moviedagger.model.repository

import com.example.moviedagger.ext.safeApiCall
import com.example.moviedagger.model.rest.ApiClient
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
        private val apiClient: ApiClient
): DataRepository {

    override suspend fun fetchMovieGenderList()= safeApiCall {
        apiClient.fetchMovieGendersList()
    }

    override suspend fun fetchGenderList(genres:Int)= safeApiCall {
        apiClient.fetchMovieList(genres)
    }
}