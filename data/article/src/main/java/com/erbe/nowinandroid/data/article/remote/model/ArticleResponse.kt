package com.erbe.nowinandroid.data.article.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleResponse(
    @field:Json(name = "id")
    val id: String?,
    @field:Json(name = "title")
    val title: String?,
    @field:Json(name = "subtitle")
    val subtitle: String?,
    @field:Json(name = "content")
    val content: String?,
    @field:Json(name = "image")
    val image: String?,
    @field:Json(name = "date")
    val date: String?,
    @field:Json(name = "time")
    val time: String?,
    @field:Json(name = "topic")
    val topic: ArticleTopicResponse?,
    @field:Json(name = "url")
    val url: String?
)