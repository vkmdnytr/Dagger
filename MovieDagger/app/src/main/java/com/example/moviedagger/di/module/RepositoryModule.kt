package com.example.moviedagger.di.module


import com.example.moviedagger.model.repository.DataRepository
import com.example.moviedagger.model.repository.DataRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDataRepostory(dataRepository: DataRepositoryImpl): DataRepository
}