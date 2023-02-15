package com.erbe.nowinandroid.core.common.network.calladapter

import com.erbe.nowinandroid.core.common.extension.serialize
import com.erbe.nowinandroid.core.common.network.model.ApiException
import com.erbe.nowinandroid.core.common.network.model.EmptyException
import com.erbe.nowinandroid.core.common.network.model.ErrorResponse
import com.erbe.nowinandroid.core.common.network.model.NetworkException
import okio.IOException
import retrofit2.HttpException

fun handleApiError(
    error: HttpException
): Throwable {
    return if (error.code() == 404) {
        EmptyException(error.message())
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