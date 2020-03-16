package com.example.moviedagger.ext

import com.example.moviedagger.common.OutCome
import java.lang.Exception

suspend fun <T : Any> safeApiCall(call: suspend () -> OutCome<T>): OutCome<T> {
    return try {
        call()
    } catch (e: Exception) {
        OutCome.Error(e.localizedMessage, e)
    }
}