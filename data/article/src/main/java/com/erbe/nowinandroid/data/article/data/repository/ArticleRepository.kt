package com.erbe.nowinandroid.data.article.data.repository

import com.erbe.nowinandroid.data.article.data.model.ArticleContentTopic
import com.erbe.nowinandroid.data.article.data.model.ArticleTopic
import com.erbe.nowinandroid.data.article.data.model.ArticleTopicContent

interface ArticleRepository {

    suspend fun getArticles(): List<ArticleContentTopic>
    suspend fun getArticleLatest(): List<ArticleContentTopic>
    suspend fun getArticleByCategory(category: String?): ArticleTopicContent
    suspend fun getArticleByCategoryLatest(): List<ArticleTopicContent>
    suspend fun getArticleDetail(id: String?): ArticleContentTopic
    suspend fun getArticleTag(): List<ArticleTopic>
}