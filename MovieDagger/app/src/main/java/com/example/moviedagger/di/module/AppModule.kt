package com.example.moviedagger.di.module

import dagger.Module

@Module(includes = arrayOf(ViewModelModule::class, NetworkModule::class))
class AppModule {

}