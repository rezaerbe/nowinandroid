package com.erbe.nowinandroid.data.article.data.repository

import com.erbe.nowinandroid.data.article.data.model.Article

interface ArticleRepository {

    suspend fun getArticles(): List<Article>
    suspend fun getArticleDetail(id: String): Article
}