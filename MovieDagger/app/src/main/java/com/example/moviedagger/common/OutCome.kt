package com.example.moviedagger.common


sealed class OutCome<out T : Any> {
    data class Success<out T : Any>(val value: T) : OutCome<T>()
    data class Error(val message: String, val cause: Exception? = null) : OutCome<Nothing>()
}
