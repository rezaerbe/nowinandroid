package com.erbe.nowinandroid.core.common.extension

import com.erbe.nowinandroid.core.common.network.model.DataException
import com.erbe.nowinandroid.core.common.network.model.EmptyException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

suspend fun <T> safeDataCall(
    dispatcher: CoroutineDispatcher,
    dataCall: suspend () -> T
): T {
    return withContext(dispatcher) {
        try {
            dataCall()
        } catch (error: Throwable) {
            throw DataException(error.message ?: "Map error")
        }
    }
}

suspend fun <T> safeDataListCall(
    dispatcher: CoroutineDispatcher,
    dataCall: suspend () -> T
): T {
    return withContext(dispatcher) {
        dataCall()
    }
}

fun <T, U> List<T>.mapSafe(
    dataCall: (T) -> U
): List<U> {
    return this.mapNotNull { data ->
        try {
            dataCall(data)
        } catch (error: Throwable) {
            null
        }
    }.ifEmpty {
        throw EmptyException("Map error")
    }
}