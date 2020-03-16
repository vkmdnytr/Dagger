package com.example.moviedagger.model.rest

import com.example.moviedagger.common.OutCome
import com.example.moviedagger.ext.safeApiCall
import com.example.moviedagger.model.entitiy.MovieGenesList
import com.example.moviedagger.model.entitiy.Page
import retrofit2.Response

class ApiClient constructor(val movieApi: MovieApi) {

    suspend fun fetchMovieGendersList(): OutCome<MovieGenesList> {
        return safeApiCall {
            val response = movieApi.getMovieGendersList()
            handleRiderResponse(response)
        }
    }
    suspend fun fetchMovieList(genres:Int): OutCome<Page> {
        return safeApiCall {
            val response = movieApi.getMovieList(genres)
            handleRiderResponse(response)
        }
    }
    private fun <T : Any> handleRiderResponse(response: Response<T>): OutCome<T> {
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return OutCome.Success(body)
            }
        }
        return OutCome.Error("Hata Olustu")

    }


}