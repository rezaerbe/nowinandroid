package com.erbe.nowinandroid.core.common.network.util

import com.erbe.nowinandroid.core.common.extension.serialize
import com.erbe.nowinandroid.core.common.network.model.ApiException
import com.erbe.nowinandroid.core.common.network.model.EmptyException
import com.erbe.nowinandroid.core.common.network.model.ErrorResponse
import com.erbe.nowinandroid.core.common.network.model.NetworkException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): T {
    return withContext(dispatcher) {
        try {
            apiCall()
        } catch (error: Throwable) {
            when (error) {
                is HttpException -> {
                    if (error.code() == 404) {
                        throw EmptyException("Not Found")
                    } else {
                        val errorResponse = error.response()?.errorBody()?.string()
                        val errorModel = errorResponse?.let { response ->
                            serialize<ErrorResponse>(response)
                        }
                        throw ApiException(
                            errorModel?.code ?: error.code(),
                            errorModel?.message ?: error.message()
                        )
                    }
                }
                is IOException -> {
                    throw NetworkException(error.message ?: "Connection error")
                }
                else -> {
                    throw error
                }
            }
        }
    }
}

fun handleApiError(
    error: HttpException
): Throwable {
    return if (error.code() == 404) {
        EmptyException("Not Found")
    } else {
        val errorResponse = error.response()?.errorBody()?.string()
        val errorModel = errorResponse?.let { response ->
            serialize<ErrorResponse>(response)
        }
        ApiException(
            errorModel?.code ?: error.code(),
            errorModel?.message ?: error.message()
        )
    }
}

fun handleNetworkError(
    error: Throwable
): Throwable {
    return when (error) {
        is IOException -> {
            NetworkException(error.message ?: "Connection error")
        }
        else -> {
            error
        }
    }
}