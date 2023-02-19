package com.erbe.nowinandroid.data.article.data.model

import com.erbe.nowinandroid.data.article.network.model.ArticleContentResponse

data class ArticleContent(
    val id: String,
    val title: String,
    val tag: String,
    val subtitle: String,
    val content: String,
    val image: String,
    val date: String,
    val time: String,
    val url: String
)

fun ArticleContentResponse.asExternalModel() =
    ArticleContent(
        id!!,
        title!!,
        tag!!,
        subtitle!!,
        content!!,
        image!!,
        date!!,
        time!!,
        url!!
    )