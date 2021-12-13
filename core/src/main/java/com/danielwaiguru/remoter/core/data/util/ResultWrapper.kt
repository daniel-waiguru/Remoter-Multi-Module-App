package com.danielwaiguru.remoter.core.data.util

import com.google.gson.GsonBuilder
import retrofit2.HttpException

sealed class ResultWrapper<out T>{
    data class Success<T>(val data: T): ResultWrapper<T>()
    data class Error<T>(val errorMessage: String?, val exception: Exception?): ResultWrapper<T>()
    object Loading: ResultWrapper<Nothing>()
}

fun parseJsonErrorResponse(throwable: HttpException): ErrorResponse? = try {
    val errorString = throwable.response()?.errorBody()?.string()
    val gson = GsonBuilder()
        .setLenient()
        .serializeNulls()
        .create()
    gson.fromJson(errorString, ErrorResponse::class.java)
} catch (e: Exception) {
    e.printStackTrace()
    null
}