package com.danielwaiguru.remoter.core.data.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException

suspend fun <T> safeCall(
    dispatcher: CoroutineDispatcher,
    block: suspend () -> T
): ResultWrapper<T> = withContext(dispatcher) {
    try {
        ResultWrapper.Success(block.invoke())
    } catch (e: HttpException) {
        val errorResponse = parseJsonErrorResponse(e)
        ResultWrapper.Error(
            errorResponse?.errorMessage,
            e
        )
    } catch (e: IOException) {
        ResultWrapper.Error(
            "Couldn't reach server, check your internet connection.",
            e
        )
    }
}