package com.erbe.nowinandroid.data.article.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleTopicResponse(
    @field:Json(name = "id")
    val id: String?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "description")
    val description: String?,
    @field:Json(name = "image")
    val image: String?,
    @field:Json(name = "total")
    val total: String?,
    @field:Json(name = "url")
    val url: String?
)