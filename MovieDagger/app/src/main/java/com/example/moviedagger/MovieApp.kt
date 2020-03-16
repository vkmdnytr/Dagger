package com.example.moviedagger

import com.example.moviedagger.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MovieApp: DaggerApplication() {

    companion object {
        var instance: MovieApp? = null
            private set
    }


    override fun applicationInjector() : AndroidInjector<MovieApp> =
        DaggerAppComponent
            .factory()
            .create(applicationContext)

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}