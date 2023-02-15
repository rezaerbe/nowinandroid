package com.erbe.nowinandroid.data.article.network.datasource

import com.erbe.nowinandroid.core.common.dispatcher.AppDispatcher
import com.erbe.nowinandroid.core.common.dispatcher.Dispatcher
import com.erbe.nowinandroid.core.common.extension.safeApiCall
import com.erbe.nowinandroid.data.article.network.model.ArticleResponse
import com.erbe.nowinandroid.data.article.network.service.ArticleService
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ArticleRemoteDataSourceImpl @Inject constructor(
    private val articleService: ArticleService,
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher
) : ArticleRemoteDataSource {

    override suspend fun getArticles(): List<ArticleResponse> =
        safeApiCall(ioDispatcher) {
            articleService.getArticles().data?.items
        }

    override suspend fun getArticleDetail(id: String): ArticleResponse =
        safeApiCall(ioDispatcher) {
            articleService.getArticleDetail(id).data
        }
}