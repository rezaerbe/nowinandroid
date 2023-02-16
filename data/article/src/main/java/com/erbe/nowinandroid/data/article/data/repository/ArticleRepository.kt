package com.erbe.nowinandroid.data.article.data.repository

import com.erbe.nowinandroid.data.article.data.model.Article
import com.erbe.nowinandroid.data.article.data.model.ArticleTopic

interface ArticleRepository {

    suspend fun getArticles(): List<Article>
    suspend fun getArticleLatest(): List<Article>
    suspend fun getArticleByCategory(category: String?): List<Article>
    suspend fun getArticleByCategoryLatest(category: String?): List<Article>
    suspend fun getArticleDetail(id: String?): Article
    suspend fun getArticleTag(): List<ArticleTopic>
}