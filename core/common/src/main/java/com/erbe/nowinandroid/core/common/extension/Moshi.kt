package com.erbe.nowinandroid.core.common.extension

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

inline fun <reified T : Any> serialize(moshi: Moshi, response: String): T? {
    val jsonAdapter: JsonAdapter<T> = moshi.adapter(T::class.java)
    return jsonAdapter.fromJson(response)
}