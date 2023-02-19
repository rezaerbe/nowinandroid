package com.erbe.nowinandroid.data.article.network.service

import com.erbe.nowinandroid.core.common.network.model.Items
import com.erbe.nowinandroid.core.common.network.model.NetworkResponse
import com.erbe.nowinandroid.data.article.network.model.ArticleContentTopicResponse
import com.erbe.nowinandroid.data.article.network.model.ArticleTopicContentResponse
import com.erbe.nowinandroid.data.article.network.model.ArticleTopicResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticleService {

    @GET("article")
    suspend fun getArticles(): NetworkResponse<Items<ArticleContentTopicResponse>>

    @GET("article/latest")
    suspend fun getArticleLatest(): NetworkResponse<Items<ArticleContentTopicResponse>>

    @GET("article/tag/{category}")
    suspend fun getArticleByCategory(
        @Path("category") category: String
    ): NetworkResponse<ArticleTopicContentResponse>

    @GET("article/tag/latest")
    suspend fun getArticleByCategoryLatest(): NetworkResponse<Items<ArticleTopicContentResponse>>

    @GET("article/{id}")
    suspend fun getArticleDetail(
        @Path("id") id: String
    ): NetworkResponse<ArticleContentTopicResponse>

    @GET("article/tag")
    suspend fun getArticleTag(): NetworkResponse<Items<ArticleTopicResponse>>
}