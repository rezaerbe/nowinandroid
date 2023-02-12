package com.erbe.nowinandroid.data.article.remote.datasource

import com.erbe.nowinandroid.data.article.remote.model.ArticleResponse

interface ArticleRemoteDataSource {

    suspend fun getArticles(): List<ArticleResponse>
}