package com.erbe.nowinandroid.data.article.model

import com.erbe.nowinandroid.data.article.remote.model.ArticleResponse

data class Article(
    val id: String,
    val title: String,
    val subtitle: String,
    val content: String,
    val image: String,
    val date: String,
    val time: String,
    val topic: ArticleTopic,
    val url: String
)

fun ArticleResponse.asExternalModel() = Article(
    id!!,
    title!!,
    subtitle!!,
    content!!,
    image!!,
    date!!,
    time!!,
    topic?.asExternalModel()!!,
    url!!
)