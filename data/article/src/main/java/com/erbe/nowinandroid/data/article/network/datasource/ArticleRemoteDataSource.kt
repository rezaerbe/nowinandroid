package com.erbe.nowinandroid.data.article.network.datasource

import com.erbe.nowinandroid.data.article.network.model.ArticleResponse
import com.erbe.nowinandroid.data.article.network.model.ArticleTopicResponse

interface ArticleRemoteDataSource {

    suspend fun getArticles(): List<ArticleResponse>
    suspend fun getArticleLatest(): List<ArticleResponse>
    suspend fun getArticleByCategory(category: String): List<ArticleResponse>
    suspend fun getArticleByCategoryLatest(category: String): List<ArticleResponse>
    suspend fun getArticleDetail(id: String): ArticleResponse
    suspend fun getArticleTag(): List<ArticleTopicResponse>
}