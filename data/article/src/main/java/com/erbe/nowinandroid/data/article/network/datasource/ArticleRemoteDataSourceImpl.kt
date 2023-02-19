package com.erbe.nowinandroid.data.article.network.datasource

import com.erbe.nowinandroid.core.common.dispatcher.AppDispatcher
import com.erbe.nowinandroid.core.common.dispatcher.Dispatcher
import com.erbe.nowinandroid.core.common.extension.safeApiCall
import com.erbe.nowinandroid.data.article.network.model.ArticleContentTopicResponse
import com.erbe.nowinandroid.data.article.network.model.ArticleTopicContentResponse
import com.erbe.nowinandroid.data.article.network.model.ArticleTopicResponse
import com.erbe.nowinandroid.data.article.network.service.ArticleService
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ArticleRemoteDataSourceImpl @Inject constructor(
    private val articleService: ArticleService,
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher
) : ArticleRemoteDataSource {

    override suspend fun getArticles(): List<ArticleContentTopicResponse> =
        safeApiCall(ioDispatcher) {
            articleService.getArticles().data?.items
        }

    override suspend fun getArticleLatest(): List<ArticleContentTopicResponse> =
        safeApiCall(ioDispatcher) {
            articleService.getArticleLatest().data?.items
        }

    override suspend fun getArticleByCategory(category: String): ArticleTopicContentResponse =
        safeApiCall(ioDispatcher) {
            articleService.getArticleByCategory(category).data
        }

    override suspend fun getArticleByCategoryLatest(): List<ArticleTopicContentResponse> =
        safeApiCall(ioDispatcher) {
            articleService.getArticleByCategoryLatest().data?.items
        }

    override suspend fun getArticleDetail(id: String): ArticleContentTopicResponse =
        safeApiCall(ioDispatcher) {
            articleService.getArticleDetail(id).data
        }

    override suspend fun getArticleTag(): List<ArticleTopicResponse> =
        safeApiCall(ioDispatcher) {
            articleService.getArticleTag().data?.items
        }
}