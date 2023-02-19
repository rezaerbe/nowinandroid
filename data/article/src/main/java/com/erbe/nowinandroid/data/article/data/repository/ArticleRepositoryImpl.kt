package com.erbe.nowinandroid.data.article.data.repository

import com.erbe.nowinandroid.core.common.dispatcher.AppDispatcher
import com.erbe.nowinandroid.core.common.dispatcher.Dispatcher
import com.erbe.nowinandroid.core.common.extension.mapSafe
import com.erbe.nowinandroid.core.common.extension.safeDataCall
import com.erbe.nowinandroid.core.common.network.model.EmptyException
import com.erbe.nowinandroid.data.article.data.model.ArticleContentTopic
import com.erbe.nowinandroid.data.article.data.model.ArticleTopic
import com.erbe.nowinandroid.data.article.data.model.ArticleTopicContent
import com.erbe.nowinandroid.data.article.data.model.asExternalModel
import com.erbe.nowinandroid.data.article.network.datasource.ArticleRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val articleRemoteDataSource: ArticleRemoteDataSource,
    @Dispatcher(AppDispatcher.Default) private val defaultDispatcher: CoroutineDispatcher
) : ArticleRepository {

    override suspend fun getArticles(): List<ArticleContentTopic> =
        safeDataCall(defaultDispatcher) {
            articleRemoteDataSource.getArticles().mapSafe { articleContentTopicResponse ->
                articleContentTopicResponse.asExternalModel()
            }
        }

    override suspend fun getArticleLatest(): List<ArticleContentTopic> =
        safeDataCall(defaultDispatcher) {
            articleRemoteDataSource.getArticleLatest().mapSafe { articleContentTopicResponse ->
                articleContentTopicResponse.asExternalModel()
            }
        }

    override suspend fun getArticleByCategory(category: String?): ArticleTopicContent =
        safeDataCall(defaultDispatcher) {
            val query = category ?: throw EmptyException("Query null")
            articleRemoteDataSource.getArticleByCategory(query).asExternalModel()
        }

    override suspend fun getArticleByCategoryLatest(): List<ArticleTopicContent> =
        safeDataCall(defaultDispatcher) {
            articleRemoteDataSource.getArticleByCategoryLatest()
                .mapSafe { articleTopicContentResponse ->
                    articleTopicContentResponse.asExternalModel()
                }
        }

    override suspend fun getArticleDetail(id: String?): ArticleContentTopic {
        return safeDataCall(defaultDispatcher) {
            val query = id ?: throw EmptyException("Query null")
            articleRemoteDataSource.getArticleDetail(query).asExternalModel()
        }
    }

    override suspend fun getArticleTag(): List<ArticleTopic> =
        safeDataCall(defaultDispatcher) {
            articleRemoteDataSource.getArticleTag().mapSafe { articleTopicResponse ->
                articleTopicResponse.asExternalModel()
            }
        }
}