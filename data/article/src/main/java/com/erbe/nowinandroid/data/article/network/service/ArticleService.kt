package com.erbe.nowinandroid.data.article.network.service

import com.erbe.nowinandroid.core.common.network.model.Items
import com.erbe.nowinandroid.core.common.network.model.NetworkResponse
import com.erbe.nowinandroid.data.article.network.model.ArticleResponse
import retrofit2.http.GET

interface ArticleService {

    @GET("/articles")
    suspend fun getArticles(): NetworkResponse<Items<ArticleResponse>>
}