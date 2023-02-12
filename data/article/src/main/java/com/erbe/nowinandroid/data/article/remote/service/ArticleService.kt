package com.erbe.nowinandroid.data.article.remote.service

import com.erbe.nowinandroid.core.common.network.model.Items
import com.erbe.nowinandroid.core.common.network.model.NetworkResponse
import com.erbe.nowinandroid.data.article.remote.model.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET

interface ArticleService {

    @GET("/articles")
    suspend fun getArticles(): Response<NetworkResponse<Items<ArticleResponse>>>
}