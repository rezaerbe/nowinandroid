package com.erbe.nowinandroid.data.article.network.service

import com.erbe.nowinandroid.core.common.network.model.Items
import com.erbe.nowinandroid.core.common.network.model.NetworkResponse
import com.erbe.nowinandroid.data.article.network.model.ArticleResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticleService {

    @GET("/article")
    suspend fun getArticles(): NetworkResponse<Items<ArticleResponse>>

    @GET("/article/{id}")
    suspend fun getArticleDetail(@Path("id") id: String): NetworkResponse<ArticleResponse>
}