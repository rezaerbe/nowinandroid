package com.erbe.nowinandroid.data.article.repository

import com.erbe.nowinandroid.data.article.model.Article

interface ArticleRepository {

    suspend fun getArticles(): List<Article>
}