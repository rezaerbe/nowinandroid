package com.erbe.nowinandroid.core.common.extension

import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

sealed interface DataState<out T> {
    data class Success<T>(val data: T) : DataState<T>
    data class Error(val exception: Throwable? = null) : DataState<Nothing>
    object Loading : DataState<Nothing>
}

suspend fun <T> MutableStateFlow<DataState<T>>.asDataState(
    action: suspend () -> T
) {
    this.update { DataState.Loading }
    try {
        val data = action()
        this.update { DataState.Success(data) }
    } catch (error: Throwable) {
        this.update { DataState.Error(error) }
    }
}

/*fun <T> Flow<T>.asDataState(): Flow<DataState<T>> {
    return this
        .map<T, DataState<T>> {
            DataState.Success(it)
        }
        .onStart { emit(DataState.Loading) }
        .catch { emit(DataState.Error(it)) }
}*/

fun <T> DataState<T>.state(
    viewLoading: View? = null,
    viewError: View? = null,
    viewSuccess: List<View>? = null
) {
    viewLoading?.let { view ->
        view.isVisible = this is DataState.Loading
    }
    viewError?.let { view ->
        view.isVisible = this is DataState.Error
    }
    viewSuccess?.let { view ->
        view.forEach { v ->
            v.isVisible = this is DataState.Success
        }
    }
}

fun <T> DataState<T>.onLoading(
    execute: () -> Unit
): DataState<T> = apply {
    if (this is DataState.Loading) {
        Log.d("TAG_PRO", "Loading")
        execute()
    }
}

fun <T> DataState<T>.onError(
    execute: (error: Throwable?) -> Unit
): DataState<T> = apply {
    if (this is DataState.Error) {
        Log.d("TAG_PRO", exception.toString())
        execute(exception)
    }
}

fun <T> DataState<T>.onSuccess(
    execute: (data: T) -> Unit
): DataState<T> = apply {
    if (this is DataState.Success) {
        Log.d("TAG_PRO", data.toString())
        execute(data)
    }
}