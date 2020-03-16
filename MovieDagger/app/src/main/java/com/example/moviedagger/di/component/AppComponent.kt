package com.example.moviedagger.di.component

import android.content.Context
import com.example.moviedagger.MovieApp
import com.example.moviedagger.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    RepositoryModule::class,
    ActivityModule::class,
    FragmentModule::class])
interface AppComponent : AndroidInjector<MovieApp> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}