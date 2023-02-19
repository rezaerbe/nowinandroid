package com.erbe.nowinandroid.core.common.extension

import com.erbe.nowinandroid.core.common.network.model.ApiException
import com.erbe.nowinandroid.core.common.network.model.EmptyException
import com.erbe.nowinandroid.core.common.network.model.ErrorResponse
import com.erbe.nowinandroid.core.common.network.model.NetworkException
import com.erbe.nowinandroid.core.common.network.model.ParsingException
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okio.EOFException
import okio.IOException
import retrofit2.HttpException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T?
): T {
    return withContext(dispatcher) {
        try {
            apiCall() ?: throw EmptyException("Network null")
        } catch (error: Throwable) {
            when (error) {
                is HttpException -> {
                    if (error.code() == 404) {
                        throw EmptyException(error.message())
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
                is EOFException, is JsonDataException -> {
                    throw ParsingException(error.message ?: "Parsing error")
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