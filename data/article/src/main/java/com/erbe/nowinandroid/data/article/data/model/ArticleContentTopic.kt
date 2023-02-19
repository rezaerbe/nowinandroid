package com.erbe.nowinandroid.data.article.data.model

import com.erbe.nowinandroid.data.article.network.model.ArticleContentTopicResponse

data class ArticleContentTopic(
    val id: String,
    val title: String,
    val tag: String,
    val subtitle: String,
    val content: String,
    val image: String,
    val date: String,
    val time: String,
    val topic: ArticleTopic,
    val url: String
)

fun ArticleContentTopicResponse.asExternalModel() =
    ArticleContentTopic(
        id!!,
        title!!,
        tag!!,
        subtitle!!,
        content!!,
        image!!,
        date!!,
        time!!,
        topic?.asExternalModel()!!,
        url!!
    )