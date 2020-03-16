package com.example.moviedagger.di

import dagger.MapKey
import kotlin.reflect.KClass
import androidx.lifecycle.ViewModel

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)