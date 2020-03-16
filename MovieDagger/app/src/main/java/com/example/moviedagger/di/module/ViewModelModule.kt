package com.example.moviedagger.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviedagger.di.ViewModelFactoryClass
import com.example.moviedagger.di.ViewModelKey
import com.example.moviedagger.ui.main.MainViewModel
import com.example.moviedagger.ui.genres.MovieViewModel
import com.example.moviedagger.ui.movielist.MovieListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factoryClass: ViewModelFactoryClass) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModel: MainViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindMFragmentMovieViewModelViewModel(githubFragmentViewModel: MovieViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    abstract fun bindMFragmentMovieListViewModel(movieListViewModelViewModel: MovieListViewModel) : ViewModel


}