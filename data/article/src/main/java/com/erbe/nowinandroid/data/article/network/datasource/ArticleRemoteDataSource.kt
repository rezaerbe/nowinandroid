package com.erbe.nowinandroid.data.article.network.datasource

import com.erbe.nowinandroid.data.article.network.model.ArticleContentTopicResponse
import com.erbe.nowinandroid.data.article.network.model.ArticleTopicContentResponse
import com.erbe.nowinandroid.data.article.network.model.ArticleTopicResponse

interface ArticleRemoteDataSource {

    suspend fun getArticles(): List<ArticleContentTopicResponse>
    suspend fun getArticleLatest(): List<ArticleContentTopicResponse>
    suspend fun getArticleByCategory(category: String): ArticleTopicContentResponse
    suspend fun getArticleByCategoryLatest(): List<ArticleTopicContentResponse>
    suspend fun getArticleDetail(id: String): ArticleContentTopicResponse
    suspend fun getArticleTag(): List<ArticleTopicResponse>
}