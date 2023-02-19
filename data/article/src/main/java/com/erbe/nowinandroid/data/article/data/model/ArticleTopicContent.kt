package com.erbe.nowinandroid.data.article.data.model

import com.erbe.nowinandroid.core.common.extension.mapSafe
import com.erbe.nowinandroid.data.article.network.model.ArticleTopicContentResponse

data class ArticleTopicContent(
    val id: String,
    val tag: String,
    val name: String,
    val description: String,
    val image: String,
    val total: String,
    val itemContent: List<ArticleContent>,
    val url: String
)

fun ArticleTopicContentResponse.asExternalModel() =
    ArticleTopicContent(
        id!!,
        tag!!,
        name!!,
        description!!,
        image!!,
        total!!,
        itemContent?.mapSafe { articleContentResponse ->
            articleContentResponse.asExternalModel()
        }!!,
        url!!
    )