package com.example.moviedagger.model.repository

import com.example.moviedagger.common.OutCome
import com.example.moviedagger.model.entitiy.MovieGenesList
import com.example.moviedagger.model.entitiy.Page
import kotlinx.coroutines.Deferred

interface DataRepository {
    suspend fun fetchMovieGenderList():OutCome<MovieGenesList>
    suspend fun fetchGenderList(genres:Int):OutCome<Page>
}