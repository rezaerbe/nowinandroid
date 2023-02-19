package com.erbe.nowinandroid.data.article.data.model

import com.erbe.nowinandroid.data.article.network.model.ArticleTopicResponse

data class ArticleTopic(
    val id: String,
    val tag: String,
    val name: String,
    val description: String,
    val image: String,
    val total: String,
    val url: String
)

fun ArticleTopicResponse.asExternalModel() =
    ArticleTopic(
        id!!,
        tag!!,
        name!!,
        description!!,
        image!!,
        total!!,
        url!!
    )