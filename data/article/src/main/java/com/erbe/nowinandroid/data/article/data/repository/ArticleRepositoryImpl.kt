package com.erbe.nowinandroid.data.article.data.repository

import com.erbe.nowinandroid.core.common.dispatcher.AppDispatcher
import com.erbe.nowinandroid.core.common.dispatcher.Dispatcher
import com.erbe.nowinandroid.core.common.network.model.EmptyException
import com.erbe.nowinandroid.data.article.data.model.Article
import com.erbe.nowinandroid.data.article.data.model.asExternalModel
import com.erbe.nowinandroid.data.article.network.datasource.ArticleRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val articleRemoteDataSource: ArticleRemoteDataSource,
    @Dispatcher(AppDispatcher.Default) private val defaultDispatcher: CoroutineDispatcher
) : ArticleRepository {

    override suspend fun getArticles(): List<Article> =
        withContext(defaultDispatcher) {
            articleRemoteDataSource.getArticles().mapNotNull { articleResponse ->
                try {
                    articleResponse.asExternalModel()
                } catch (error: Throwable) {
                    null
                }
            }.ifEmpty {
                throw EmptyException("Empty")
            }
        }
}