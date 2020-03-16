package com.example.moviedagger.model.rest

import com.example.moviedagger.model.entitiy.MovieGenesList
import com.example.moviedagger.model.entitiy.Page
import com.example.moviedagger.model.rest.ServiceUrls.BASE_URL
import com.example.moviedagger.model.rest.ServiceUrls.GENRE_LIST_URL
import com.example.moviedagger.model.rest.ServiceUrls.MOVIE_LIST_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET(GENRE_LIST_URL)
    suspend fun getMovieGendersList(): Response<MovieGenesList>
    @GET(MOVIE_LIST_URL)
    suspend fun getMovieList(@Query("with_genres") with_genres:Int): Response<Page>
}