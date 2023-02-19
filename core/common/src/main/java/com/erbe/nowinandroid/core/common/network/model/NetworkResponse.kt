package com.erbe.nowinandroid.core.common.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkResponse<T>(
    @field:Json(name = "code")
    val code: Int?,
    @field:Json(name = "message")
    val message: String?,
    @field:Json(name = "data")
    val data: T?
)

@JsonClass(generateAdapter = true)
data class Items<T>(
    @field:Json(name = "items")
    val items: List<T>?
)