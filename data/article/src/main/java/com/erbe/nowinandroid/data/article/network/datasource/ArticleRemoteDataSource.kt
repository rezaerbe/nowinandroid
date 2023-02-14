package com.erbe.nowinandroid.data.article.network.datasource

import com.erbe.nowinandroid.data.article.network.model.ArticleResponse

interface ArticleRemoteDataSource {

    suspend fun getArticles(): List<ArticleResponse>
}