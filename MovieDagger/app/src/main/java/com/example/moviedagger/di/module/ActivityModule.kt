package com.example.moviedagger.di.module

import com.example.moviedagger.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = arrayOf(FragmentModule::class))
    abstract fun contributeMainActivity() : MainActivity
}