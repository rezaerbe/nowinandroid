package com.erbe.nowinandroid.core.common.extension

import com.squareup.moshi.Moshi

inline fun <reified T> serialize(response: String): T? {
    return try {
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(T::class.java)
        jsonAdapter.fromJson(response)
    } catch (error: Throwable) {
        null
    }
}