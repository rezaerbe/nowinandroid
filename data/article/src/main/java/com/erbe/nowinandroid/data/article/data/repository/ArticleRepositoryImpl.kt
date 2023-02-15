package com.erbe.nowinandroid.data.article.data.repository

import com.erbe.nowinandroid.core.common.dispatcher.AppDispatcher
import com.erbe.nowinandroid.core.common.dispatcher.Dispatcher
import com.erbe.nowinandroid.core.common.extension.mapSafe
import com.erbe.nowinandroid.core.common.extension.safeDataCall
import com.erbe.nowinandroid.core.common.extension.safeDataListCall
import com.erbe.nowinandroid.data.article.data.model.Article
import com.erbe.nowinandroid.data.article.data.model.asExternalModel
import com.erbe.nowinandroid.data.article.network.datasource.ArticleRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val articleRemoteDataSource: ArticleRemoteDataSource,
    @Dispatcher(AppDispatcher.Default) private val defaultDispatcher: CoroutineDispatcher
) : ArticleRepository {

    override suspend fun getArticles(): List<Article> =
        safeDataListCall(defaultDispatcher) {
            articleRemoteDataSource.getArticles().mapSafe { articleResponse ->
                articleResponse.asExternalModel()
            }
        }

    override suspend fun getArticleDetail(id: String): Article =
        safeDataCall(defaultDispatcher) {
            articleRemoteDataSource.getArticleDetail(id).asExternalModel()
        }
}