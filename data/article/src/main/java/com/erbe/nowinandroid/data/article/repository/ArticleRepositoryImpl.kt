package com.erbe.nowinandroid.data.article.repository

import com.erbe.nowinandroid.core.common.network.model.CustomException
import com.erbe.nowinandroid.data.article.model.Article
import com.erbe.nowinandroid.data.article.model.asExternalModel
import com.erbe.nowinandroid.data.article.remote.datasource.ArticleRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val articleRemoteDataSource: ArticleRemoteDataSource,
    private val coroutineScope: CoroutineScope
) : ArticleRepository {

    override suspend fun getArticles(): List<Article> =
        withContext(coroutineScope.coroutineContext) {
            val articleResponse = articleRemoteDataSource.getArticles()
            if (articleResponse.isNotEmpty()) {
                val articles = articleResponse.mapNotNull { article ->
                    try {
                        article.asExternalModel()
                    } catch (error: Throwable) {
                        null
                    }
                }
                articles.ifEmpty {
                    throw CustomException(404, "Not Found")
                }
            } else {
                throw CustomException(404, "Not Found")
            }
        }
}