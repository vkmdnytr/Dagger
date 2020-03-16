package com.example.moviedagger.di.module

import com.example.moviedagger.ui.genres.MovieFragment
import com.example.moviedagger.ui.movielist.MovieListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeMovieFragment() : MovieFragment
    @ContributesAndroidInjector
    abstract fun contributeMovieListFragment() : MovieListFragment
}
