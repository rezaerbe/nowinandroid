package com.erbe.nowinandroid.core.common.extension

import com.erbe.nowinandroid.core.common.network.model.EmptyException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

suspend fun <T> safeDataCall(
    dispatcher: CoroutineDispatcher,
    dataCall: suspend () -> T
): T {
    return withContext(dispatcher) {
        dataCall()
    }
}

fun <T, U> List<T>.mapSafe(
    mapCall: (T) -> U
): List<U> {
    return this.mapNotNull { data ->
        try {
            mapCall(data)
        } catch (error: Throwable) {
            null
        }
    }.ifEmpty {
        throw EmptyException("Data null")
    }
}